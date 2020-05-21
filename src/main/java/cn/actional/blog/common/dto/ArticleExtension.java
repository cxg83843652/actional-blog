package cn.actional.blog.common.dto;

import cn.actional.blog.domain.ActionArticles;
import cn.actional.blog.domain.ActionLabels;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @auther actional
 * @create 2020-05-21
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleExtension extends ActionArticles implements Serializable {
    private String labelString;


    public String getLabelString() {
        StringBuffer sb = new StringBuffer();
        List<ActionLabels> labelList = getLabelList();
        labelList.forEach(label -> {
            sb.append(label.getLabelName()).append(",");
        });
        sb.substring(0,sb.length() - 1 );
        return sb.toString();
    }


}
