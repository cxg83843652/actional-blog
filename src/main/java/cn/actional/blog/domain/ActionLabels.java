package cn.actional.blog.domain;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 *  @auther  actional
 *  @create 2020-05-18
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "action_labels")
public class ActionLabels implements Serializable {
    /**
     * 标签主键
     */
    @Id
    private Long labelId;

    /**
     * 标签名
     */
    private String labelName;
}
