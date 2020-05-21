package cn.actional.blog.service.impl;

import cn.actional.blog.domain.ActionComments;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import cn.actional.blog.mapper.ActionCommentsMapper;
import cn.actional.blog.service.ActionCommentsService;
/**
 *  @auther  actional
 *  @create 2020-05-20
 *
 */
@Service
public class ActionCommentsServiceImpl implements ActionCommentsService{

    @Resource
    private ActionCommentsMapper actionCommentsMapper;


    @Override
    public Integer selectCount() {
        return actionCommentsMapper.selectCount(new ActionComments());
    }
}
