package com.ikamobile.cache;

import java.io.Closeable;
import java.io.IOException;
import java.util.Set;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class RedisManager implements Closeable {

    private int defaultexpireTime = 864000;

    private JedisPool jedisPool;

    public void setJedisPool(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    /**
     * get value from redis
     *
     * @param key
     * @return
     */
    public String get(String key) {
        String value = null;

        Jedis jedis = null;
        try  {
             jedis = jedisPool.getResource();
            value = jedis.get(key);
        } catch (Exception e) {
//            log.error("error occured when get value with key = " + key + " from redis", e);
        } finally {
            jedis.close();
        }

        return value;
    }

    /**
     * set
     *
     * @param key
     * @param value
     * @return
     */
    public String set(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.set(key, value);
            if (this.defaultexpireTime != 0) {
                jedis.expire(key, this.defaultexpireTime);
            }
        } catch (Exception e) {
//            log.error("error occured when set value = " + value + " with key = " + key + " from redis", e);
        } finally {
            jedis.close();
        }
        return value;
    }

    /**
     * set
     *
     * @param key
     * @param value
     * @param expireTime
     * @return
     */
    public String set(String key, String value, int expireTime) {

        Jedis jedis = jedisPool.getResource();
        try  {

            jedis.set(key, value);
            if (expireTime != 0) {
                jedis.expire(key, expireTime);
            }
        } catch (Exception e) {
//            log.error("error occured when set value = " + value + " with key = " + key + " and expireTime = " + expireTime + " from redis", e);
        } finally {
            jedis.close();
        }
        return value;
    }

    /**
     * del
     *
     * @param key
     */
    public Long del(String key) {
        Long deleted = 0L;
        Jedis jedis = jedisPool.getResource();
        try  {
            deleted = jedis.del(key);
        } catch (Exception e) {
//            log.error("error occured when delete value with key = " + key + " from redis", e);
        }finally {
            jedis.close();
        }
        return deleted;
    }

    /**
     * del
     *
     * @param keys
     */
    public Long del(String[] keys) {
        Long deleted = 0L;
        Jedis jedis = jedisPool.getResource();
        try  {
            deleted = jedis.del(keys);
        } catch (Exception e) {
//            log.error("error occured when del  with keys = " + keys + " from redis", e);
        }finally {
            jedis.close();
        }
        return deleted;
    }

    /**
     * flush
     */
    public void flushDB() {
        Jedis jedis = jedisPool.getResource();
        try {
            jedis.flushDB();
        } catch (Exception e) {
//            log.error("error occured when flush db", e);
        }finally {
            jedis.close();
        }
    }

    /**
     * size
     */
    public Long dbSize() {
        Long dbSize = 0L;
        Jedis jedis = jedisPool.getResource();
        try  {
            dbSize = jedis.dbSize();
        } catch (Exception e) {
//            log.error("error occured when get db size", e);
        }finally {
            jedis.close();
        }
        return dbSize;
    }

    /**
     * keys
     *
     * @param pattern
     * @return
     */
    public Set<String> keys(String pattern) {
        Set<String> keys = null;
        Jedis jedis = jedisPool.getResource();
        try  {
            keys = jedis.keys(pattern);
        } catch (Exception e) {
//            log.error("error occured when get keys", e);
        }finally {
            jedis.close();
        }
        return keys;
    }



    /**
     * get value from redis
     *
     * @param key
     * @return
     */
    public byte[] get(byte[] key) {
        byte[] value = null;
        Jedis jedis = jedisPool.getResource();
        try  {
            value = jedis.get(key);
        } catch (Exception e) {
//            log.error("error occured when get value with key = " + key + " from redis", e);
        }finally {
            jedis.close();
        }
        return value;
    }

    /**
     * set
     *
     * @param key
     * @param value
     * @return
     */
    public byte[] set(byte[] key, byte[] value) {
        Jedis jedis = jedisPool.getResource();
        try {
            jedis.set(key, value);
            if (this.defaultexpireTime != 0) {
                jedis.expire(key, this.defaultexpireTime);
            }
        } catch (Exception e) {
//            log.error("error occured when set value = " + value + " with key = " + key + " from redis", e);
        }finally {
            jedis.close();
        }
        return value;
    }

    /**
     * set
     *
     * @param key
     * @param value
     * @param expireTime
     * @return
     */
    public byte[] set(byte[] key, byte[] value, int expireTime) {
        Jedis jedis = jedisPool.getResource();
        try  {
            jedis.set(key, value);
            if (expireTime != 0) {
                jedis.expire(key, expireTime);
            }
        } catch (Exception e) {
//            log.error("error occured when set value = " + value + " with key = " + key + " and expireTime = " + expireTime + " from redis", e);
        }finally {
            jedis.close();
        }
        return value;
    }

    /**
     * del
     *
     * @param key
     */
    public Long del(byte[] key) {
        Long deleted = 0L;
        Jedis jedis = jedisPool.getResource();
        try  {
            deleted = jedis.del(key);
        } catch (Exception e) {
//            log.error("error occured when delete value with key = " + key + " from redis", e);
        }finally {
            jedis.close();
        }
        return deleted;
    }

    /**
     * del
     *
     * @param keys
     */
    public Long del(byte[][] keys) {
        Long deleted = 0L;
        Jedis jedis = jedisPool.getResource();

        try{
            deleted = jedis.del(keys);
        } catch (Exception e) {
//            log.error("error occured when del  with keys = " + keys + " from redis", e);
        }finally {
            jedis.close();
        }
        return deleted;
    }

    /**
     * keys
     *
     * @param pattern
     * @return
     */
    public Set<byte[]> keys(byte[] pattern) {
        Set<byte[]> keys = null;
        Jedis jedis = jedisPool.getResource();
        try  {
            keys = jedis.keys(pattern);
        } catch (Exception e) {
//            log.error("error occured when get keys", e);
        }finally {
            jedis.close();
        }
        return keys;
    }

//    @Override
    public void close() throws IOException {
        jedisPool.close();
    }

}
