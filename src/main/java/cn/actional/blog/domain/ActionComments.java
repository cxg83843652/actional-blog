package cn.actional.blog.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *  @auther  actional
 *  @create 2020-05-20
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "action_comments")
public class ActionComments implements Serializable {
    /**
     * 主键
     */
    @Id
    @Column(name = "comment_id")
    @GeneratedValue(generator = "JDBC")
    private Long commentId;

    /**
     * 发表评论id
     */
    @Column(name = "submitter_id")
    private Long submitterId;

    /**
     * 点赞数
     */
    @Column(name = "comment_like_count")
    private Long commentLikeCount;

    /**
     * 评论日期
     */
    @Column(name = "comment_date")
    private Date commentDate;

    /**
     * 评论内容
     */
    @Column(name = "comment_content")
    private String commentContent;

    /**
     * 父评论ID
     */
    @Column(name = "parent_comment_id")
    private Long parentCommentId;
}
