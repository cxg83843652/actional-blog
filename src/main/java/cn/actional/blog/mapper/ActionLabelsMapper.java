package cn.actional.blog.mapper;

import cn.actional.blog.common.dto.BaseCount;
import cn.actional.blog.domain.ActionLabels;
import tk.mybatis.mapper.common.Mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  @auther  actional
 *  @create 2020-05-18
 *
 */
public interface ActionLabelsMapper extends Mapper<ActionLabels> {


    List<HashMap> selectByGroup();


    Long[] selectMiddleByArticleId(Long articleId);

    ActionLabels selectByKey(Long labelId);

    Long[] selectMiddleByLabelId(Long labelId);

    List<BaseCount<ActionLabels>> selectMiddleAll();



    int insertMiddle(Long articleId, Long labelId);

    int insertOne(ActionLabels labels);

    int deleteMiddle(Long articleId);
}
