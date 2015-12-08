package com.ikamobile.cache;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.cache.Cache;
import org.springframework.cache.support.AbstractCacheManager;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

public class RedisCacheManager extends AbstractCacheManager implements DisposableBean {
    private static final int DEFAULT_EXPIRE_TIME = 86400; // 1 day, second
    
    private int defaultExpireTime = DEFAULT_EXPIRE_TIME;
    
    private RedisManager redisManager;


    public void setRedisManager(RedisManager redisManager) {
        this.redisManager = redisManager;
    }

    public void setExpireTimeConfig(Map<String, Integer> expireTimeConfig) {
        this.expireTimeConfig = expireTimeConfig;
    }

    private Map<String, Integer> expireTimeConfig;
    @Override
    public Cache getCache(String name) {
        Cache cache = super.getCache(name);
        if (cache == null) {
            int expireTime = defaultExpireTime;
            if (expireTimeConfig != null && expireTimeConfig.containsKey(name)) {
                expireTime = expireTimeConfig.get(name);
            }
            cache = new RedisCache(name, redisManager, expireTime);
            addCache(cache);
        }
        return cache;
    }

//    @Override
    public void destroy() throws Exception {
    }

    @Override
    protected Collection<? extends Cache> loadCaches() {
        return Collections.EMPTY_LIST;
    }

}
