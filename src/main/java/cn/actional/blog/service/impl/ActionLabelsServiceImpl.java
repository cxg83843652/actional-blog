package cn.actional.blog.service.impl;

import cn.actional.blog.common.cache.CacheNameSpaceEnum;
import cn.actional.blog.common.cache.annotation.QueryCache;
import cn.actional.blog.common.cache.annotation.UpdateCache;
import cn.actional.blog.common.dto.BaseCommonMap;
import cn.actional.blog.common.dto.BaseCount;
import cn.actional.blog.domain.ActionArticles;
import cn.actional.blog.domain.ActionCategories;
import cn.actional.blog.domain.ActionLabels;
import cn.actional.blog.service.ActionArticlesService;
import cn.actional.blog.utils.PageQueryUtil;
import cn.actional.blog.utils.PageResult;
import com.fasterxml.jackson.databind.util.BeanUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Insert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import cn.actional.blog.mapper.ActionLabelsMapper;
import cn.actional.blog.service.ActionLabelsService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 *  @auther  actional
 *  @create 2020-05-18
 *
 */
@Service
public class ActionLabelsServiceImpl implements ActionLabelsService{

    @Resource
    private ActionLabelsMapper labelsMapper;

    @Resource
    private ActionArticlesService articlesService;

    private static final Logger LOGGER = LoggerFactory.getLogger(ActionLabelsServiceImpl.class);


    /**
     * 查询出标签下文章数大于一的
     * @return
     */
    @QueryCache(nameSpace = CacheNameSpaceEnum.LABEL_LIST)
    @Override
    public List<BaseCount<ActionLabels>> selectByGroup() {
        List<HashMap> hashMaps = labelsMapper.selectByGroup();
        if (CollectionUtils.isEmpty(hashMaps)) {
            return null;
        }
        ArrayList<BaseCount<ActionLabels>> list = new ArrayList<>();
        hashMaps.forEach(map -> {
            ActionLabels labels = new ActionLabels((Long) map.get("label_id"), (String) map.get("label_name"));
            list.add(new BaseCount<ActionLabels>(labels,(Long) map.get("count")));
        });
        return list;
    }

    /**
     * 根据文章id查询标签列表
     * @param articleId
     * @return
     */
    @Override
    public List<ActionLabels> selectByArticleId(Long articleId) {
        //查询中间表，获取标签id数组
        Long[] ids = labelsMapper.selectMiddleByArticleId(articleId);
        List<ActionLabels> labelList = new ArrayList<>();

        for (int i = 0; i < ids.length; i++) {
            ActionLabels label = labelsMapper.selectByKey(ids[i]);
            labelList.add(label);
        }
        return labelList;
    }

    /**
     * 标签页面展示，可根据id 或 当前页查询
     * @param labelId
     * @param currentPage
     * @return
     */
    @Override
    public BaseCommonMap<BaseCount<ActionLabels>, List<ActionArticles>> getLabelMap(Long labelId, Integer currentPage) {
        BaseCommonMap<BaseCount<ActionLabels>, List<ActionArticles>> map = new BaseCommonMap<>();
        if (labelId == null || labelId == 0) {
            if (currentPage == null) {
                currentPage = 1;
            }
            PageHelper.startPage(currentPage,3);
            List<BaseCount<ActionLabels>> baseCounts = labelsMapper.selectMiddleAll();
            PageInfo pageInfo = new PageInfo(baseCounts);
            baseCounts.forEach(base -> {
                List<ActionArticles> articlesList = articlesService.selectByLabelId(base.getItem().getLabelId());
                map.put(base,articlesList);
            });
            map.setPageInfo(pageInfo);
            return map;
        }
        getLabelMap(labelId,map);
        return map;
    }


    public BaseCommonMap<BaseCount<ActionLabels>, List<ActionArticles>> getLabelMap(Long labelId,BaseCommonMap<BaseCount<ActionLabels>, List<ActionArticles>> map) {
        List<ActionArticles> articlesList = articlesService.selectByLabelId(labelId);
        ActionLabels actionLabels = labelsMapper.selectByPrimaryKey(labelId);
        BaseCount<ActionLabels> baseCount = new BaseCount<>(actionLabels, 1L);
        map.put(baseCount,articlesList);
        return map;
    }

    @Override
    public Long[] selectMiddleByLabelId(Long labelId) {
        return labelsMapper.selectMiddleByLabelId(labelId);
    }

    @Override
    public Integer selectCount() {
        return labelsMapper.selectCount(new ActionLabels());
    }

    @Override
    public PageResult selectByPage(PageQueryUtil pageUtil) {
        PageHelper.startPage(pageUtil.getPage(),pageUtil.getLimit());
        List<ActionLabels> labelsList = labelsMapper.selectAll();
        PageInfo<ActionLabels> pageInfo = new PageInfo<>(labelsList);
        PageResult pageResult = new PageResult(pageInfo.getTotal(),pageUtil.getLimit(),pageInfo.getPages() ,pageUtil.getPage(),labelsList  );
        return pageResult;
    }

    @UpdateCache(nameSpace = CacheNameSpaceEnum.LABEL_LIST)
    @Transactional
    @Override
    public int insert(String tagName) {
        ActionLabels labels = new ActionLabels(null, tagName);
        ActionLabels selectOne = labelsMapper.selectOne(labels);
        if (labels != null) {
            return 0;
        }
        return insert(labels);
    }

    @UpdateCache(nameSpace = CacheNameSpaceEnum.LABEL_LIST)
    @Transactional
    @Override
    public int insert(ActionLabels labels) {
        return labelsMapper.insertOne(labels);
    }


    @UpdateCache(nameSpace = CacheNameSpaceEnum.LABEL_LIST)
    @Transactional
    @Override
    public boolean deleteBatch(Integer[] ids) {
        Boolean status = false;
        for (int i = 0; i < ids.length; i++) {
            status = deleteByPrimaryKey(ids[i].longValue());
            if (!status) {
                LOGGER.error("标签删除失败");
                throw new RuntimeException();
            }
        }
        return status;
    }

    @UpdateCache(nameSpace = CacheNameSpaceEnum.LABEL_LIST)
    @Transactional
    @Override
    public Boolean deleteByPrimaryKey(Long labelId) {
        return labelsMapper.deleteByPrimaryKey(labelId) == 1;
    }

    @Transactional
    @Override
    public int insertMiddle(Long articleId, Long labelId) {
        return labelsMapper.insertMiddle(articleId,labelId);
    }

    @Transactional
    @Override
    public int deleteMiddle(Long articleId) {
        return labelsMapper.deleteMiddle(articleId);
    }

    @Override
    public ActionLabels selectOne(ActionLabels label) {
        return labelsMapper.selectOne(label);
    }
}
