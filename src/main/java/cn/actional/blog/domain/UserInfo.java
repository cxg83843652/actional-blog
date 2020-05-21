package cn.actional.blog.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 载荷对象
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo implements Serializable {

    private Long id;

    private String nickname;

    private String username;

    public UserInfo(Long id, String nickname) {
        this.id = id;
        this.nickname = nickname;
    }
}
