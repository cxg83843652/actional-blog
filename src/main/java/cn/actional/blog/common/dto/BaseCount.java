package cn.actional.blog.common.dto;

import cn.actional.blog.domain.ActionCategories;
import com.github.pagehelper.PageInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @auther actional
 * @create 2020-05-18
 * 封装需要带总数的类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseCount<T> implements Serializable {
    private T item;
    private Long count;


}
