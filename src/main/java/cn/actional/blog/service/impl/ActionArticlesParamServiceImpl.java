package cn.actional.blog.service.impl;

import cn.actional.blog.domain.ActionArticlesParam;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import cn.actional.blog.mapper.ActionArticlesParamMapper;
import cn.actional.blog.service.ActionArticlesParamService;
/**
 *  @auther  actional
 *  @create 2020-05-20
 *
 */
@Service
public class ActionArticlesParamServiceImpl implements ActionArticlesParamService{

    @Resource
    private ActionArticlesParamMapper actionArticlesParamMapper;

    @Override
    public ActionArticlesParam selectByPrimaryKey(Long articleId) {
        return actionArticlesParamMapper.selectByPrimaryKey(articleId);
    }

    @Override
    public int update(ActionArticlesParam articlesParam) {
        return actionArticlesParamMapper.updateByPrimaryKeySelective(articlesParam);
    }
}
