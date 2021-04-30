package com.dinghao.core.interceptor.impl;


import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dinghao.common.constant.Constants;
import com.dinghao.common.filter.RepeatedlyRequestWrapper;
import com.dinghao.common.utils.StringUtils;
import com.dinghao.common.utils.http.HttpHelper;
import com.dinghao.core.interceptor.RepeatSubmitInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.cache.RedisCache;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;
import java.util.HashMap;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 判断请求url和数据是否和上一次相同，
 * 如果和上次相同，则是重复提交表单。 有效时间为10秒内。
 *
 * @author ruoyi
 */
@Component
public class SameUrlDataInterceptor extends RepeatSubmitInterceptor {
    public final String REPEAT_PARAMS = "repeatParams";

    public final String REPEAT_TIME = "repeatTime";

    /**
     * 令牌自定义标识
     */
    @Value("${token.header}")
    private String header;

    @Autowired
    private StringRedisTemplate redisTemplate;


    /**
     * 间隔时间，单位:秒 默认10秒
     * <p>
     * 两次相同参数的请求，如果间隔时间大于该参数，系统不会认定为重复提交的数据
     */
    private int intervalTime = 10;

    public void setIntervalTime(int intervalTime) {
        this.intervalTime = intervalTime;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean isRepeatSubmit(HttpServletRequest request) throws IOException {
       // HttpServletRequestWrapper repeatedlyRequest = (HttpServletRequestWrapper) request;
        RepeatedlyRequestWrapper repeatedlyRequest = new RepeatedlyRequestWrapper(request);
        String nowParams = HttpHelper.getBodyString(repeatedlyRequest);

        // body参数为空，获取Parameter的数据
        if (StringUtils.isEmpty(nowParams)) {
            nowParams = JSONObject.toJSONString(request.getParameterMap());
        }
        Map<String, Object> nowDataMap = new HashMap<String, Object>();
        nowDataMap.put(REPEAT_PARAMS, nowParams);
        nowDataMap.put(REPEAT_TIME, System.currentTimeMillis());

        // 请求地址（作为存放cache的key值）
        String url = request.getRequestURI();
        // 唯一标识（指定key + 消息头）
        String cache_repeat_key = Constants.REPEAT_SUBMIT_KEY + request.getHeader(header);
        String objStr = redisTemplate.opsForValue().get(cache_repeat_key);
        if (StrUtil.isNotBlank(objStr)) {
            Map<String, Object> sessionMap = JSON.parseObject(objStr, HashMap.class);
            if (sessionMap.containsKey(url)) {
                Map<String, Object> preDataMap = (Map<String, Object>) sessionMap.get(url);
                if (compareParams(nowDataMap, preDataMap) && compareTime(nowDataMap, preDataMap)) {
                    return true;
                }
            }
        }
        Map<String, Object> cacheMap = new HashMap<String, Object>();
        cacheMap.put(url, nowDataMap);
        redisTemplate.opsForValue().set(cache_repeat_key, JSON.toJSONString(cacheMap), intervalTime, TimeUnit.SECONDS);
        //redisCache.setCacheObject(cache_repeat_key, cacheMap, intervalTime, TimeUnit.SECONDS);
        return false;
    }

    /**
     * 判断参数是否相同
     */
    private boolean compareParams(Map<String, Object> nowMap, Map<String, Object> preMap) {
        String nowParams = (String) nowMap.get(REPEAT_PARAMS);
        String preParams = (String) preMap.get(REPEAT_PARAMS);
        return nowParams.equals(preParams);
    }

    /**
     * 判断两次间隔时间
     */
    private boolean compareTime(Map<String, Object> nowMap, Map<String, Object> preMap) {
        long time1 = (Long) nowMap.get(REPEAT_TIME);
        long time2 = (Long) preMap.get(REPEAT_TIME);
        if ((time1 - time2) < (this.intervalTime * 1000)) {
            return true;
        }
        return false;
    }
}
