package cn.actional.blog.common.dto;

import cn.actional.blog.domain.ActionArticles;
import cn.actional.blog.domain.ActionCategories;
import com.github.pagehelper.PageInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @auther actional
 * @create 2020-05-19
 */
@Data
@NoArgsConstructor
public class BaseCommonMap<K,V>  implements Serializable {

    private Map<K,V> map = new HashMap<>();
    private PageInfo pageInfo;

    public void put(K k,V v) {
        this.map.put(k,v);
    }
}
