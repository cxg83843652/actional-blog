package cn.actional.blog.service;

import cn.actional.blog.domain.ActionLink;
import cn.actional.blog.utils.PageQueryUtil;
import cn.actional.blog.utils.PageResult;

import java.util.List;

/**
 * @auther actional
 * @create 2020-05-20
 */
public interface ActionLinkService {


    List<ActionLink> selectAll();

    Integer selectCount();

    PageResult selectByPage(PageQueryUtil pageUtil);

    Boolean insert(ActionLink link);

    ActionLink selectByPrimaryKey(Integer id);

    Boolean update(ActionLink tempLink);

    Boolean deleteBatch(Integer[] ids);


    Boolean deleteByPrimaryKey(Long linkId);
}
