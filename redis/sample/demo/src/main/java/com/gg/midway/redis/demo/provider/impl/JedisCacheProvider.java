package com.gg.midway.redis.demo.provider.impl;

import com.gg.midway.redis.demo.provider.ICacheProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

// @Component
public class JedisCacheProvider implements ICacheProvider {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Override
    public void setString(String key, String value) {
        System.out.println("setString() stringRedisTemplate : " + stringRedisTemplate);
        stringRedisTemplate.opsForValue().set(key, value);
    }

    @Override
    public String getString(String key) {
        System.out.println("getString() stringRedisTemplate : " + stringRedisTemplate);
        return stringRedisTemplate.opsForValue().get(key);
    }
}
