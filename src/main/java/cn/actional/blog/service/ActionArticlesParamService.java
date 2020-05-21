package cn.actional.blog.service;

import cn.actional.blog.domain.ActionArticlesParam;

/**
 * @auther actional
 * @create 2020-05-20
 */
public interface ActionArticlesParamService {


    ActionArticlesParam selectByPrimaryKey(Long articleId);

    int update(ActionArticlesParam articlesParam);
}
