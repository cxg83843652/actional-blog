package cn.actional.blog.service;

import cn.actional.blog.common.dto.BaseCount;
import cn.actional.blog.domain.ActionArticles;
import cn.actional.blog.utils.PageQueryUtil;
import cn.actional.blog.utils.PageResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

/**
 *  @auther  actional
 *  @create 2020-05-18
 *
 */
public interface ActionArticlesService{

    PageInfo getArticlesByPage(Integer currentPage,Integer pageSize);

    List<ActionArticles> getLatest();


    List<ActionArticles> selectByYear(String year);


    Integer selectTotal();

    List<ActionArticles> selectByCategoryId(Long categoryId);

    List<ActionArticles> selectByLabelId(Long labelId);

    Integer selectCount();

    PageResult selectArticleByPage(PageQueryUtil pageUtil);

    ActionArticles selectByArticleId(Long articleId);

    String insertArticle(ActionArticles articles);


    Long getPreArticleId(Long articleId);

    Long getNextArticleId(Long articleId);

    Map<String,List<ActionArticles>> getArticlesByDate(String date);

    String updateArticle(ActionArticles articles);

    Boolean deleteBatch(Integer[] ids);
}
