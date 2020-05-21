package cn.actional.blog.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *  @auther  actional
 *  @create 2020-05-20
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "action_user")
public class ActionUser implements Serializable {
    /**
     * 主键
     */
    @Id
    @Column(name = "user_id")
    @GeneratedValue(generator = "JDBC")
    private Long userId;

    /**
     * 昵称
     */
    @Column(name = "user_nickname")
    private String userNickname;

    /**
     * 用户名
     */
    @Column(name = "user_name")
    private String userName;

    /**
     * 密码
     */
    @Column(name = "user_password")
    private String userPassword;

    /**
     * 加密盐
     */
    @Column(name = "user_salt")
    private String userSalt;

    /**
     * 邮件
     */
    @Column(name = "user_email")
    private String userEmail;

    /**
     * 注册时间
     */
    @Column(name = "user_regis_time")
    private Date userRegisTime;
}
