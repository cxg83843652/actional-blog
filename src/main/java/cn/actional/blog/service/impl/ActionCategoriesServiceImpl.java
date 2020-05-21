package cn.actional.blog.service.impl;

import cn.actional.blog.common.cache.CacheNameSpaceEnum;
import cn.actional.blog.common.cache.annotation.QueryCache;
import cn.actional.blog.common.cache.annotation.UpdateCache;
import cn.actional.blog.common.dto.BaseCommonMap;
import cn.actional.blog.common.dto.BaseCount;
import cn.actional.blog.domain.ActionArticles;
import cn.actional.blog.service.ActionArticlesService;
import cn.actional.blog.utils.DateFormatUtil;
import cn.actional.blog.utils.PageQueryUtil;
import cn.actional.blog.utils.PageResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import javax.annotation.Resource;

import cn.actional.blog.mapper.ActionCategoriesMapper;
import cn.actional.blog.domain.ActionCategories;
import cn.actional.blog.service.ActionCategoriesService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @auther actional
 * @create 2020-05-18
 */
@Service
public class ActionCategoriesServiceImpl implements ActionCategoriesService {

    @Resource
    private ActionCategoriesMapper actionCategoriesMapper;

    @Resource
    private ActionArticlesService articlesService;

    @UpdateCache(nameSpace = CacheNameSpaceEnum.CATEGORY_BY_GROUP)
    @Transactional
    @Override
    public int deleteByPrimaryKey(Long categoryId) {
        return actionCategoriesMapper.deleteByPrimaryKey(categoryId);
    }

    @UpdateCache(nameSpace = CacheNameSpaceEnum.CATEGORY_BY_GROUP)
    @Transactional
    @Override
    public int insert(ActionCategories record) {
        return actionCategoriesMapper.insert(record);
    }

    @UpdateCache(nameSpace = CacheNameSpaceEnum.CATEGORY_BY_GROUP)
    @Transactional
    @Override
    public int insertSelective(ActionCategories record) {
        return actionCategoriesMapper.insertSelective(record);
    }

    @Override
    public ActionCategories selectByPrimaryKey(Long categoryId) {

        return actionCategoriesMapper.selectByPrimaryKey(categoryId);
    }

    @UpdateCache(nameSpace = CacheNameSpaceEnum.CATEGORY_BY_GROUP)
    @Transactional
    @Override
    public int updateByPrimaryKeySelective(ActionCategories record) {
        return actionCategoriesMapper.updateByPrimaryKeySelective(record);
    }

    @UpdateCache(nameSpace = CacheNameSpaceEnum.CATEGORY_BY_GROUP)
    @Transactional
    @Override
    public Boolean updateByPrimaryKey(ActionCategories record) {
        return actionCategoriesMapper.updateByPrimaryKey(record) == 1;
    }


    /**
     *  分类页面展示，可根据id 或 当前页查询
     * @param categoryId
     * @param currentPage
     * @return
     */
    @Override
    public BaseCommonMap<BaseCount<ActionCategories>, List<ActionArticles>> getCategoryMap(Long categoryId, Integer currentPage) {
        BaseCommonMap<BaseCount<ActionCategories>, List<ActionArticles>> map = new BaseCommonMap<>();
        if (categoryId == null || categoryId == 0) {
            if (currentPage == null) {
                currentPage = 1;
            }

            PageHelper.startPage(currentPage,3);
            List<BaseCount<ActionCategories>> baseCounts = actionCategoriesMapper.selectByGroup();
            PageInfo pageInfo = new PageInfo(baseCounts);
            baseCounts.forEach(item -> {
                List<ActionArticles> articlesList = articlesService.selectByCategoryId(item.getItem().getCategoryId());
                setDateFormat(articlesList);
                map.put(item,articlesList);
            });

            map.setPageInfo(pageInfo);
            return map;
        }
        List<ActionArticles> articlesList = articlesService.selectByCategoryId(categoryId);
        setDateFormat(articlesList);
        BaseCount<ActionCategories> baseCount = new BaseCount<>();
        baseCount.setCount((long) articlesList.size());
        baseCount.setItem(actionCategoriesMapper.selectByPrimaryKey(categoryId));
        map.put(baseCount,articlesList);
        return map;
    }

    private void setDateFormat(List<ActionArticles> articlesList) {
        articlesList.forEach(article -> {
            article.setFormatDate(DateFormatUtil.formatYMD(article.getArticleDate(),"yyyy-MM-dd"));
        });
    }


    /**
     * 查询出分类下的文章数大于一的
     *
     * @return
     */
    @QueryCache(nameSpace = CacheNameSpaceEnum.CATEGORY_BY_GROUP)
    @Override
    public List<BaseCount<ActionCategories>> selectByGroup() {
        return this.actionCategoriesMapper.selectByGroup();
    }

    @Override
    public Integer selectCount() {
        return actionCategoriesMapper.selectCount(new ActionCategories());
    }

    @Override
    public PageResult selectByPage(PageQueryUtil pageUtil) {
        PageHelper.startPage(pageUtil.getPage(),pageUtil.getLimit());
        List<ActionCategories> categoriesList = actionCategoriesMapper.selectAll();
        PageInfo<ActionCategories> pageInfo = new PageInfo<>(categoriesList);
        PageResult pageResult = new PageResult(pageInfo.getTotal(),pageUtil.getLimit(),pageInfo.getPages() ,pageUtil.getPage(),categoriesList  );
        return pageResult;
    }

    @UpdateCache(nameSpace = CacheNameSpaceEnum.CATEGORY_BY_GROUP)
    @Transactional
    @Override
    public Boolean insert(String categoryName) {
        ActionCategories categories = new ActionCategories(null,categoryName);
        ActionCategories selectOne = actionCategoriesMapper.selectOne(categories);
        if (selectOne != null) {
            return false;
        }
        return actionCategoriesMapper.insertSelective(categories) == 1;
    }

    @Transactional
    @Override
    public boolean deleteBatch(Integer[] ids) {
        Boolean status = false;
        for (int i = 0; i < ids.length; i++) {
            status = deleteByPrimaryKey(ids[i].longValue()) == 1;
            if (!status) {
                throw new RuntimeException("删除失败");
            }
        }
        return status;
    }

    @Override
    public List<ActionCategories> selectAll() {
        return actionCategoriesMapper.selectAll();
    }


}
