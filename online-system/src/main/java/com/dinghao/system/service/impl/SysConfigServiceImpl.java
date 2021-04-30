package com.dinghao.system.service.impl;


import cn.hutool.core.convert.Convert;
import com.dinghao.common.constant.Constants;
import com.dinghao.common.constant.UserConstants;
import com.dinghao.common.enums.DataSourceType;
import com.dinghao.common.utils.StringUtils;
import com.dinghao.system.domain.SysConfig;
import com.dinghao.system.mapper.SysConfigMapper;
import com.dinghao.system.service.ISysConfigService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.cache.RedisCache;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Collection;

/**
 * 参数配置 服务层实现
 * 
 * @author ruoyi
 */
@Service
@Slf4j
public class SysConfigServiceImpl implements ISysConfigService
{

    @Autowired
    private SysConfigMapper configMapper;

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 根据参数键名查询参数值
     */

    @Override
    public String selectConfigByKey(String configKey) {
        String configValue = redisTemplate.opsForValue().get(getCacheKey(configKey));
        if (StringUtils.isNotEmpty(configValue))
        {
            return configValue;
        }
        SysConfig config = new SysConfig();
        config.setConfigKey(configKey);
        SysConfig retConfig = configMapper.selectConfig(config);
        if (StringUtils.isNotNull(retConfig))
        {
            redisTemplate.opsForValue().set(getCacheKey(configKey), retConfig.getConfigValue());
            return retConfig.getConfigValue();
        }
        return StringUtils.EMPTY;
    }

    /**
     * 设置cache key
     *
     * @param configKey 参数键
     * @return 缓存键key
     */
    private String getCacheKey(String configKey)
    {
        return Constants.SYS_CONFIG_KEY + configKey;
    }
}
