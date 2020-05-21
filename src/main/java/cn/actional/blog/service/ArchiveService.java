package cn.actional.blog.service;

import cn.actional.blog.common.dto.BaseCount;
import cn.actional.blog.domain.ActionArticles;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @auther actional
 * @create 2020-05-18
 */
public interface ArchiveService {
    /**
     * 获取归档信息
     * @return
     */
    List<BaseCount<Date>> getArchive();

    Map<String, List<ActionArticles>> getArchiveGroupByYear();
}
