package cn.actional.blog.service;

import cn.actional.blog.common.dto.BaseCommonMap;
import cn.actional.blog.common.dto.BaseCount;
import cn.actional.blog.domain.ActionArticles;
import cn.actional.blog.domain.ActionCategories;
import cn.actional.blog.utils.PageQueryUtil;
import cn.actional.blog.utils.PageResult;

import java.util.List;
import java.util.Map;

/**
 * @auther actional
 * @create 2020-05-18
 */
public interface ActionCategoriesService {



    public int deleteByPrimaryKey(Long categoryId);


    public int insert(ActionCategories record);


    public int insertSelective(ActionCategories record);


    public ActionCategories selectByPrimaryKey(Long categoryId);

    /**
     * 查询出分类下的文章数大于一的
     * @return
     */
    public List<BaseCount<ActionCategories>> selectByGroup();


    public int updateByPrimaryKeySelective(ActionCategories record) ;


    public Boolean updateByPrimaryKey(ActionCategories record);


    BaseCommonMap<BaseCount<ActionCategories>,List<ActionArticles>> getCategoryMap(Long categoryId, Integer currentPage);


    public Integer selectCount();

    PageResult selectByPage(PageQueryUtil pageUtil);


    public Boolean insert(String categoryName);

    boolean deleteBatch(Integer[] ids);

    List<ActionCategories> selectAll();
}

