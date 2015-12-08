package com.ikamobile.cache;

import org.springframework.cache.Cache;
import org.springframework.cache.support.SimpleValueWrapper;

import java.util.Set;

public class RedisCache implements Cache {
    private String name;
    private RedisManager cache;
    private int expires;

    public RedisCache(String name, RedisManager cache, int expires) {
        this.name = name;
        this.cache = cache;
        this.expires = expires;
    }

    public String getName() {
        return this.name;
    }

    //    @Override
    public Object getNativeCache() {
        return cache;
    }

    /**
     * 实现@Cacheable注解
     */
//    @Override
    public ValueWrapper get(Object key) {
        Object value = getValue(key);
        if (value != null) {
            return new SimpleValueWrapper(value);
        }
        return null;
    }

//    @Override
    public <T> T get(Object key, Class<T> type) {
        Object value = getValue(key);
        if (value != null) {
            if (type != null && !type.isInstance(value)) {
//                //log.error("Cached value is not of required type {}, value {}", type.getName(), value);
            } else {
//                log.info("Cache {} key {} hit.", name, key);
                return (T) value;
            }
        }
        return null;
    }

    private Object getValue(Object key) {
        if (key != null) {
            String uniqueKey = uniqueKey(key);
            String valueSerial = cache.get(uniqueKey);
            Object value = ObjectJsonConvertor.toObject(valueSerial);
//            log.debug("uniqueKey={}, valueSerial={}", new Object[] { uniqueKey, valueSerial });
            if (value != null) {
//                log.info("Cache {} key {} hit.", name, key);
                return value;
            } else {
//                log.info("Cache {} key {} miss.", name, key);/
            }
        } else {
            //log.error("Cache store route error.");
        }
        return null;
    }

    /**
     * 实现@CachePut注解
     */
//    @Override
    public void put(Object key, Object value) {
        if (key != null && value != null) {
            String uniqueKey = uniqueKey(key);
            String valueSerial = ObjectJsonConvertor.toString(value);
            String result = cache.set(uniqueKey, valueSerial, expires);
//            log.debug("uniqueKey={}, expires={}, valueSerial={}, result={}", new Object[] { uniqueKey,
//                    String.valueOf(expires), valueSerial, result });
        } else {
//            //log.warn("Key or value is null. Key={}, value={}", key, value);
        }
    }

    /**
     * 实现@CacheEvict注解
     */
//    @Override
    public void evict(Object key) {
        if (key != null) {
            String uniqueKey = uniqueKey(key);
            Long removeCount = cache.del(uniqueKey);
//            log.debug("uniqueKey={}, removeCount={}", new Object[] { uniqueKey, String.valueOf(removeCount) });
        } else {
            //log.warn("Key is null.");
        }
    }

//    @Override
    public void clear() {
        long deleteCount = 0;
        Set<String> keySet = cache.keys(name + "*");
        String[] keyArray = keySet.toArray(new String[keySet.size()]);
        if (keyArray.length > 0) {
            deleteCount += cache.del(keyArray);
        }
//        log.debug("count={}", deleteCount);
    }

    /**
     * make unique key by prepend cache name
     * 
     * @param key
     *            logic key
     * @return the unique key with cache name
     */
    private String uniqueKey(Object key) {
        return new StringBuilder().append(this.name).append(":").append(String.valueOf(key)).toString();
    }

}
