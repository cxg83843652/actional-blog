package cn.actional.blog.service.impl;

import cn.actional.blog.common.cache.CacheNameSpaceEnum;
import cn.actional.blog.common.cache.annotation.QueryCache;
import cn.actional.blog.common.cache.annotation.UpdateCache;
import cn.actional.blog.domain.ActionConfig;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import cn.actional.blog.mapper.ActionConfigMapper;
import cn.actional.blog.service.ActionConfigService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *  @auther  actional
 *  @create 2020-05-20
 *
 */
@Service
public class ActionConfigServiceImpl implements ActionConfigService{

    @Resource
    private ActionConfigMapper actionConfigMapper;


    /**
     * 获取所有配置信息
     * @return
     */
    @QueryCache(nameSpace = CacheNameSpaceEnum.CONFIG)
    @Override
    public Map<String, String> selectAll() {
        List<ActionConfig> configList = selectList();
        Map<String, String> configMap = configList.stream().collect(Collectors.toMap(ActionConfig::getConfigName, ActionConfig::getConfigValue));
        return configMap;
    }

    @UpdateCache(nameSpace = CacheNameSpaceEnum.CONFIG)
    @Transactional
    @Override
    public int updateConfig(String configName, String configValue) {
        ActionConfig actionConfig = new ActionConfig();

        actionConfig.setConfigName(configName);
        ActionConfig config = actionConfigMapper.selectOne(actionConfig);
        if (config == null) {
            return 0;
        }
        config.setConfigValue(configValue);
        return actionConfigMapper.updateByPrimaryKeySelective(config);
    }

    @Override
    public List<ActionConfig> selectList() {
        return actionConfigMapper.selectAll();
    }
}
