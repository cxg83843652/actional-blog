package cn.actional.blog.mapper;

import cn.actional.blog.common.dto.BaseCount;
import cn.actional.blog.domain.ActionCategories;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @auther actional
 * @create 2020-05-18
 */
public interface ActionCategoriesMapper extends Mapper<ActionCategories> {


    List<BaseCount<ActionCategories>> selectByGroup();
}
