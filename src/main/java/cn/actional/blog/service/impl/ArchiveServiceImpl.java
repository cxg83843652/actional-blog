package cn.actional.blog.service.impl;

import cn.actional.blog.common.cache.CacheNameSpaceEnum;
import cn.actional.blog.common.cache.annotation.QueryCache;
import cn.actional.blog.common.dto.BaseCount;
import cn.actional.blog.domain.ActionArticles;
import cn.actional.blog.mapper.ArchiveMapper;
import cn.actional.blog.service.ActionArticlesService;
import cn.actional.blog.service.ArchiveService;
import cn.actional.blog.utils.DateFormatUtil;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.time.Year;
import java.util.*;

/**
 * @auther actional
 * @create 2020-05-18
 */
@Service
public class ArchiveServiceImpl implements ArchiveService {

    @Autowired
    private ArchiveMapper archiveMapper;

    @Autowired
    private ActionArticlesService articlesService;

    /**
     * 获取年月分组下的文章数
     * @return
     */
    @QueryCache(nameSpace = CacheNameSpaceEnum.ARCHIVE_LIST)
    @Override
    public List<BaseCount<Date>> getArchive() {
        return archiveMapper.getArchive();
    }

    /**
     * 根据年份查询文章
     * @return
     */
    @Override
    public Map<String, List<ActionArticles>> getArchiveGroupByYear() {
        List<String> archiveGroupByYear = this.archiveMapper.getArchiveGroupByYear();
        Map<String,List<ActionArticles>> map = new TreeMap<>( (String o1, String o2) -> o2.compareTo(o1));
        archiveGroupByYear.forEach(year -> {
            List<ActionArticles> articlesList = articlesService.selectByYear(year);
            articlesList.forEach(article -> {
                article.setFormatDate(
                    DateFormatUtil.formatYMD(article.getArticleDate(),"yyyy-MM-dd")
                );
            });
            map.put(year,articlesList);
        });
        return map;
    }
}
