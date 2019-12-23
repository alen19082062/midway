package com.gg.midway.cache.dao;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Component
public class UserDao {
    private Map<Long, User> userMap;

    @PostConstruct
    public void init() {
        //模拟数据库
        userMap = new HashMap<Long, User>();
        User user=new User();
        user.setUserId(6545);
        user.setUserName("big-ball");
        user.setPassword("11111");
        user.setPhone("13900001111");
        userMap.put(1L, user );

        user=new User();
        user.setUserId(6545);
        user.setUserName("small-ball");
        user.setPassword("22222");
        user.setPhone("139000022222");
        userMap.put(2L, user );
    }

    @Cacheable("user")  // 注解key属性可以执行缓存对象user(可以理解为一个map)的key
    public User getUser(Long userId) {
        System.out.println("getUser() 查询数据库:userId ->" + userId);
        return userMap.get(userId);
    }

    // @Cacheable(value = "nameCache", key = "#name")
    @Cacheable(value = "c22", key = "#name")
    public User getUserByName(Long userId, String name) {
        System.out.println("getUserByName() 查询数据库:userId ->" + userId);
        return userMap.get(userId);
    }

    // @Cacheable("nameCache")
    @Cacheable("c22")
    public User getUserByName(String name) {
        System.out.println("getUserByName() 查询数据库:userName : " + name);
        for (Long k : userMap.keySet()) {
            if (userMap.get(k).equals(name)) {
                return userMap.get(k);
            }
        }
        return null;
    }

    @CachePut("user") // 与Cacheable区别就是Cacheable先看缓存如果有，直接缓存换回，CachePut则是每次都会调用并且把返回值放到缓存
    public User getUser2(Long userId) {
        System.out.println("getUser2() 查询数据库:userId : " + userId);
        return userMap.get(userId);
    }

    @CacheEvict("user")
    public void removeFromCache(Long userId) {
        System.out.println("removeFromCache() userId : " + userId);
        return ;
    }

}
