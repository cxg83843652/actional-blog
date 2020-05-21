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
/**
    * 友链图标
    */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "action_link")
public class ActionLink implements Serializable {
    /**
     * 友链表主键id
     */
    @Id
    @Column(name = "link_id")
    @GeneratedValue(generator = "JDBC")
    private Integer linkId;

    /**
     * 友链类别 0-友链 1-推荐 2-个人网站
     */
    @Column(name = "link_type")
    private Byte linkType;

    /**
     * 网站名称
     */
    @Column(name = "link_name")
    private String linkName;

    /**
     * 网站链接
     */
    @Column(name = "link_url")
    private String linkUrl;

    /**
     * 网站描述
     */
    @Column(name = "link_description")
    private String linkDescription;

    /**
     * 用于列表排序
     */
    @Column(name = "link_rank")
    private Integer linkRank;

    /**
     * 是否删除 0-未删除 1-已删除
     */
    @Column(name = "del")
    private Byte del;

    /**
     * 添加时间
     */
    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "link_logo")
    private String linkLogo;
}
