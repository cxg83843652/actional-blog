package cn.actional.blog.domain;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 *  @auther  actional
 *  @create 2020-05-20
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "action_articles_param")
public class ActionArticlesParam implements Serializable {
    /**
     * 文章主键
     */
    @Id
    @Column(name = "article_id")
    private Long articleId;

    /**
     * 评论总数
     */
    @Column(name = "article_comment_count")
    private Long articleCommentCount;

    /**
     * 点赞数
     */
    @Column(name = "article_thumb_up")
    private Long articleThumbUp;

    /**
     * 浏览量
     */
    @Column(name = "article_views")
    private Long articleViews;

    /**
     * 文章内容
     */
    @Column(name = "article_content")
    private String articleContent;
}
