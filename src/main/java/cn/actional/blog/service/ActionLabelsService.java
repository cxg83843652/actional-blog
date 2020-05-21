package cn.actional.blog.service;

import cn.actional.blog.common.dto.BaseCommonMap;
import cn.actional.blog.common.dto.BaseCount;
import cn.actional.blog.domain.ActionArticles;
import cn.actional.blog.domain.ActionLabels;
import cn.actional.blog.utils.PageQueryUtil;
import cn.actional.blog.utils.PageResult;

import java.util.List;

/**
 *  @auther  actional
 *  @create 2020-05-18
 *
 */
public interface ActionLabelsService{

    /**
     * 查询出标签下的文章数大于一的
     * @return
     */
    List<BaseCount<ActionLabels>> selectByGroup();

    /**
     * 根据文章id查询标签列表
     * @param articleId
     * @return
     */
    List<ActionLabels> selectByArticleId(Long articleId);

    BaseCommonMap<BaseCount<ActionLabels>,List<ActionArticles>> getLabelMap(Long labelId, Integer currentPage);

    Long[] selectMiddleByLabelId(Long labelId);

    Integer selectCount();

    PageResult selectByPage(PageQueryUtil pageUtil);

    int insert(String tagName);

    int insert(ActionLabels labels);

    boolean deleteBatch(Integer[] ids);


    Boolean deleteByPrimaryKey(Long labelId);

    int insertMiddle(Long articleId, Long labelId);

    int deleteMiddle(Long articleId);

    ActionLabels selectOne(ActionLabels label);
}
