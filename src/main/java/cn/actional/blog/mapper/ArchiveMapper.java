package cn.actional.blog.mapper;

import cn.actional.blog.common.dto.BaseCount;
import cn.actional.blog.domain.ActionArticles;
import tk.mybatis.mapper.common.Mapper;

import java.util.Date;
import java.util.List;

/**
 * @auther actional
 * @create 2020-05-18
 */
public interface ArchiveMapper {
    List<BaseCount<Date>> getArchive();

    List<String> getArchiveGroupByYear();
}
