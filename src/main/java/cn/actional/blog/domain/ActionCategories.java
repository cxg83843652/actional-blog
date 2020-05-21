package cn.actional.blog.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @auther actional
 * @create 2020-05-18
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "action_categories")
public class ActionCategories implements Serializable {
    /**
     * 分类主键
     */
    @Id
    private Long categoryId;

    /**
     * 分类名
     */
    private String categoryName;
}
