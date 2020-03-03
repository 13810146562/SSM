package com.zqq.ssm.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

/**
 * @Author zqq
 * @Date 2020/3/3  15:53
 */
public class RedisCacheTransfer {
    @Autowired
    public void setJedisConnectionFactory(JedisConnectionFactory jedisConnectionFactory){
        RedisCache.setJedisConnectionFactory(jedisConnectionFactory);
    }
}
