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
@Table(name = "action_config")
public class ActionConfig implements Serializable {
    @Id
    @Column(name = "config_id")
    @GeneratedValue(generator = "JDBC")
    private Long configId;

    /**
     * 配置项的名称
     */
    @Column(name = "config_name")
    private String configName;

    /**
     * 配置项的值
     */
    @Column(name = "config_value")
    private String configValue;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;
}
