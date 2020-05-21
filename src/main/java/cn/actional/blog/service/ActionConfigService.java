package cn.actional.blog.service;

import cn.actional.blog.domain.ActionConfig;

import java.util.List;
import java.util.Map;

/**
 * @auther actional
 * @create 2020-05-20
 */
public interface ActionConfigService {


    Map<String, String> selectAll();

    int updateConfig(String configName, String configValue);

    List<ActionConfig> selectList();
}
