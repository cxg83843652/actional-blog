package cn.actional.blog.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *  @auther  actional
 *  @create 2020-05-18
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "action_articles")
public class ActionArticles implements Serializable {
    /**
     * 文章主键
     */
    @Id
    @Column(name = "article_id")
    @GeneratedValue(generator = "JDBC")
    private Long articleId;

    /**
     * 文章标题
     */
    @Column(name = "article_title")
    private String articleTitle;

    /**
     * 标记：转载，原创，翻译
     */
    @Column(name = "article_flag")
    private String articleFlag;

    /**
     * 发表时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @Column(name = "article_date")
    private Date articleDate;

    /**
     * 是否推荐
     */
    @Column(name = "recommend")
    private Boolean recommend;

    /**
     * 是否删除
     */
    @Column(name = "del")
    private Boolean del;

    /**
     * 0-草稿1-发布
     */
    @Column(name = "article_status")
    private Boolean articleStatus;

    private ActionCategories category;

    @Transient
    private String formatDate;

    private List<ActionLabels> labelList;

    private ActionArticlesParam articlesParam;

}
