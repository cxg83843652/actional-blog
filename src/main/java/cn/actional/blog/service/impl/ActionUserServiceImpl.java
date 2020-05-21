package cn.actional.blog.service.impl;

import cn.actional.blog.config.JwtProperties;
import cn.actional.blog.domain.ActionUser;
import cn.actional.blog.domain.UserInfo;
import cn.actional.blog.utils.CodecUtils;
import cn.actional.blog.utils.JwtUtils;
import cn.actional.blog.utils.MD5Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import cn.actional.blog.mapper.ActionUserMapper;
import cn.actional.blog.service.ActionUserService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *  @auther  actional
 *  @create 2020-05-20
 *
 */
@Service
public class ActionUserServiceImpl implements ActionUserService{

    @Resource
    private ActionUserMapper actionUserMapper;



    @Override
    public UserInfo login(String userName, String password) {
        ActionUser user = new ActionUser();
        user.setUserName(userName);
        user = this.actionUserMapper.selectOne(user);
        if (user == null) {
            return null;
        }
        //获取盐，对用户输入的密码进行加盐加密
        password = CodecUtils.md5Hex(password, user.getUserSalt());
        System.out.println(password);
        System.out.println(user.getUserPassword());
        //和数据库中的密码比较
        if (StringUtils.equals(password,user.getUserPassword())) {
            //设置要在前台显示的用户信息
            try {
                UserInfo userInfo = new UserInfo(user.getUserId(),user.getUserNickname());

                /*String token = JwtUtils.generateToken(userInfo, this.jwtProperties.getPrivateKey(), this.jwtProperties.getExpire());
                return token;*/
                return userInfo;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    @Override
    public UserInfo selectByPrimaryKey(Integer loginUserId) {
        ActionUser user = actionUserMapper.selectByPrimaryKey(loginUserId);
        return new UserInfo(user.getUserId(),user.getUserNickname(),user.getUserName());
    }

    @Transactional
    @Override
    public boolean updatePassword(Long loginUserId, String originalPassword, String newPassword) {
        ActionUser actionUser = new ActionUser();
        actionUser.setUserId(loginUserId);
        actionUser.setUserPassword(MD5Util.MD5Encode(originalPassword,"utf-8"));
        actionUser = actionUserMapper.selectOne(actionUser);
        actionUser.setUserPassword(MD5Util.MD5Encode(newPassword,"utf-8"));
        return actionUserMapper.updateByPrimaryKey(actionUser) == 1;
    }


    @Transactional
    @Override
    public boolean updateName(Long loginUserId, String loginUserName, String nickName) {
        ActionUser user = new ActionUser();
        user.setUserId(loginUserId);
        user = actionUserMapper.selectByPrimaryKey(user);
        user.setUserName(loginUserName);
        user.setUserNickname(nickName);
        return actionUserMapper.updateByPrimaryKey(user) == 1;
    }
}
