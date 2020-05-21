package cn.actional.blog.service;

import cn.actional.blog.domain.ActionUser;
import cn.actional.blog.domain.UserInfo;

/**
 * @auther actional
 * @create 2020-05-20
 */
public interface ActionUserService {


    UserInfo login(String userName, String password);

    UserInfo selectByPrimaryKey(Integer loginUserId);

    boolean updatePassword(Long loginUserId, String originalPassword, String newPassword);

    boolean updateName(Long loginUserId, String loginUserName, String nickName);
}
