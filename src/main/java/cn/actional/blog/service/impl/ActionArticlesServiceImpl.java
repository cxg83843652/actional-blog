package cn.actional.blog.service.impl;

import cn.actional.blog.common.cache.CacheNameSpaceEnum;
import cn.actional.blog.common.cache.annotation.QueryCache;
import cn.actional.blog.common.cache.annotation.UpdateCache;
import cn.actional.blog.domain.ActionArticles;
import cn.actional.blog.domain.ActionArticlesParam;
import cn.actional.blog.domain.ActionLabels;
import cn.actional.blog.mapper.ActionCategoriesMapper;
import cn.actional.blog.service.ActionArticlesParamService;
import cn.actional.blog.service.ActionLabelsService;
import cn.actional.blog.utils.DateFormatUtil;
import cn.actional.blog.utils.PageQueryUtil;
import cn.actional.blog.utils.PageResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import cn.actional.blog.mapper.ActionArticlesMapper;
import cn.actional.blog.service.ActionArticlesService;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.text.ParseException;
import java.util.*;

/**
 *  @auther  actional
 *  @create 2020-05-18
 *
 */
@Service
public class ActionArticlesServiceImpl implements ActionArticlesService{

    @Resource
    private ActionArticlesMapper articlesMapper;

    @Resource
    private ActionCategoriesMapper categoriesMapper;

    @Resource
    private ActionLabelsService labelsService;

    @Resource
    private ActionArticlesParamService articlesParamService;



    @Override
    public PageInfo getArticlesByPage(Integer currentPage,Integer pageSize) {
        PageHelper.startPage(currentPage,pageSize);
        List<ActionArticles> articles = articlesMapper.getArticles();
        articles.forEach(article -> {
            //获取标签列表
            List<ActionLabels> labelList = labelsService.selectByArticleId(article.getArticleId());
            article.setLabelList(labelList);
            //格式化日期
            String formatYMD = DateFormatUtil.formatYMD(article.getArticleDate(), "yyyy-MM-dd");
            article.setFormatDate(formatYMD);
        });
        PageInfo pageInfo = new PageInfo(articles);
        return pageInfo;
    }



    /**
     * 获取最新文章
     * @return
     */
    @QueryCache(nameSpace = CacheNameSpaceEnum.ARTICLE_LATEST)
    @Override
    public List<ActionArticles> getLatest() {
        List<ActionArticles> articlesList = articlesMapper.getLatest();
        articlesList.forEach(article -> {
            String formatYMD = DateFormatUtil.formatYMD(article.getArticleDate(), "2020-05-04 ");
            article.setFormatDate(formatYMD);
        });
        return articlesList;
    }

    @Override
    public List<ActionArticles> selectByYear(String year) {
        return articlesMapper.selectByYear(year);
    }


    @QueryCache(nameSpace = CacheNameSpaceEnum.ARTICLE_TOTAL)
    @Override
    public Integer selectTotal() {
        ActionArticles articles = new ActionArticles();
        articles.setDel(false);
        return articlesMapper.selectCount(articles);
    }

    @Override
    public List<ActionArticles> selectByCategoryId(Long categoryId) {
        return articlesMapper.selectByCategoryId(categoryId);
    }

    @Override
    public List<ActionArticles> selectByLabelId(Long labelId) {
        Long[] articleIds = labelsService.selectMiddleByLabelId(labelId);
        List<ActionArticles> list = new ArrayList<>();
        for (int i = 0; i < articleIds.length; i++) {
            ActionArticles articles = articlesMapper.selectByPrimaryKey(articleIds[i]);
            list.add(articles);
        }
        return list;
    }

    @Override
    public Integer selectCount() {
        return articlesMapper.selectCount(new ActionArticles());
    }

    @Override
    public PageResult selectArticleByPage(PageQueryUtil pageUtil) {
        PageHelper.startPage(pageUtil.getPage(),pageUtil.getLimit());
        List<ActionArticles> articlesList = articlesMapper.selectByParam(pageUtil);
        PageInfo<ActionArticles> pageInfo = new PageInfo<>(articlesList);
        articlesList.forEach(article -> {
            ActionArticlesParam articlesParam = articlesParamService.selectByPrimaryKey(article.getArticleId());
            article.setArticlesParam(articlesParam);
        });
        return new PageResult(pageInfo.getTotal(),pageInfo.getPageSize(),pageInfo.getPages(),pageInfo.getPageNum(),articlesList);
    }

    @Override
    public ActionArticles selectByArticleId(Long articleId) {
        List<ActionLabels> labelsList = labelsService.selectByArticleId(articleId);
        ActionArticles articles = articlesMapper.selectByArticleId(articleId);
        articles.setLabelList(labelsList);
        return articles;
    }




    /**
     *  新增一篇文章
     * @param articles
     * @return
     */
    @UpdateCache(nameSpace = {CacheNameSpaceEnum.ARCHIVE_LIST,CacheNameSpaceEnum.ARTICLE_LATEST,CacheNameSpaceEnum.ARTICLE_TOTAL})
    @Transactional
    @Override
    public String insertArticle(ActionArticles articles) {
        articles.setArticleDate(new Date());
        articles.setDel(false);
        int flag = articlesMapper.insertArticle(articles);
        if (flag > 0) {
            //插入成功才对详情表和标签表进行操作
            //操作详情表
            ActionArticlesParam articlesParam = articles.getArticlesParam();
            articlesParam.setArticleCommentCount(0L);
            articlesParam.setArticleThumbUp(0L);
            articlesParam.setArticleViews(0L);
            articlesParam.setArticleId(articles.getArticleId());
            if ( articlesMapper.insertArticleParam(articlesParam) == 0 ) {
                throw new RuntimeException("**********插入详情表失败**********");
            }
            //操作标签表

            for (ActionLabels label : articles.getLabelList()) {
                label = labelsService.selectOne(label);
                if (label == null) {
                    System.out.println("11111111");
                    labelsService.insert(label);
                }
                //插入中间表
                if (labelsService.insertMiddle(articles.getArticleId(),label.getLabelId()) == 0) {
                    throw new RuntimeException("**********插入中间表失败**********");
                }
            }



            return "success";
        }


        return "保存失败";
    }

    /**
     * 查询上一篇的文章id
     * @param articleId
     * @return
     */
    @Override
    public Long getPreArticleId(Long articleId) {
        return articlesMapper.getPreArticleId(articleId);
    }


    /**
     * 查询下一篇的文章id
     * @param articleId
     * @return
     */
    @Override
    public Long getNextArticleId(Long articleId) {
        return articlesMapper.getNextArticleId(articleId);
    }

    @Override
    public Map<String, List<ActionArticles>> getArticlesByDate(String date) {
        if (StringUtils.isNoneBlank(date)) {
            try {
                String conversion = DateFormatUtil.strConversionStr(date, "MM月 yyyy", "yyyy-MM");
                Map<String, List<ActionArticles>> map = new HashMap<>();
                List<ActionArticles> articlesList = articlesMapper.selectByDate(conversion);
                articlesList.forEach(article -> {
                    try {
                        article.setFormatDate(DateFormatUtil.strConversionStr(date, "MM月 yyyy","yyyy-MM-dd"));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                });
                map.put(conversion.split("-")[0],articlesList);
                return map;
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Transactional
    @Override
    public String updateArticle(ActionArticles articles) {
        ActionArticles flag = articlesMapper.selectByPrimaryKey(articles.getArticleId());
        if (flag == null) {
            return "数据不存在";
        }
        Boolean statu = false;
        //修改中间表
        List<ActionLabels> labelList = articles.getLabelList();
        //先删除原有中间表
        labelsService.deleteMiddle(articles.getArticleId());
        //新增中间表
        for (ActionLabels label : articles.getLabelList()) {
            label = labelsService.selectOne(label);
            if (label == null) {
                labelsService.insert(label);
            }
            //插入中间表
            if (labelsService.insertMiddle(articles.getArticleId(),label.getLabelId()) == 0) {
                throw new RuntimeException("**********插入中间表失败**********");
            }
        }


        //详情表
        statu = articlesParamService.update(articles.getArticlesParam()) == 1;
        //主表
        statu =  articlesMapper.updateByPrimaryKeySelective(articles) == 1;
        if (!statu) {
            throw new RuntimeException("修改失败");
        }
        return "success";
    }

    @Override
    public Boolean deleteBatch(Integer[] ids) {
        return articlesMapper.deleteBatch(ids) > 0;
    }
}
