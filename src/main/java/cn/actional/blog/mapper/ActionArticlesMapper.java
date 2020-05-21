package cn.actional.blog.mapper;

import cn.actional.blog.common.dto.BaseCount;
import cn.actional.blog.domain.ActionArticles;
import cn.actional.blog.domain.ActionArticlesParam;
import cn.actional.blog.utils.PageQueryUtil;
import tk.mybatis.mapper.common.Mapper;

import java.util.Date;
import java.util.List;

/**
 *  @auther  actional
 *  @create 2020-05-18
 *
 */
public interface ActionArticlesMapper extends Mapper<ActionArticles> {


    List<ActionArticles> getArticles();

    List<ActionArticles> getLatest();

    List<ActionArticles> selectByYear(String year);

    List<ActionArticles> selectByCategoryId(Long categoryId);

    List<ActionArticles> selectByParam(PageQueryUtil pageUtil);

    ActionArticles selectByArticleId(Long articleId);

    int insertArticle(ActionArticles articles);

    int insertArticleParam(ActionArticlesParam articlesParam);

    Long getPreArticleId(Long articleId);

    Long getNextArticleId(Long articleId);

    List<ActionArticles> selectByDate(String date);

    int deleteBatch(Integer[] ids);
}
