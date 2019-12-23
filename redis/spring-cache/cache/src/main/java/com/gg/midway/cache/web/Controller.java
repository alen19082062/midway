package com.gg.midway.cache.web;

import com.gg.midway.cache.dao.User;
import com.gg.midway.cache.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Controller
 * 消费者控制层
 */
@RestController
public class Controller {

    @Autowired(required = false)
    private StringRedisTemplate stringRedisTemplate;

    @RequestMapping("/set/{name}")
    public String set(@PathVariable("name") String name) {
        stringRedisTemplate.opsForValue().set("name",name );
        System.out.println("set() Set value : " + name );
        return "set value  : " + name ;
    }

    @RequestMapping("/hi")
    public Map<String, String> hi() {
        String value = stringRedisTemplate.opsForValue().get("name" );
        System.out.println("hi() value : " + value );
        System.out.println("hi() name : " + value );
        Map<String,String> map = new HashMap<>() ;
        map.put("name",value);
        return map ;
    }

    @Autowired(required = false)
    private RedisTemplate<String, Serializable> redisTemplate;
    // User 对象
    @RequestMapping("/set_user")
    public String set2() {
        User user=new User();
        user.setUserId(6545);
        user.setUserName("big-ball");
        user.setPassword("22222");
        user.setPhone("13900001111");
        if(!redisTemplate.hasKey("big-ball")) {
            redisTemplate.opsForValue().set("big-ball",user);
            System.out.println("user set ok !");
        }
        User user2=(User) redisTemplate.opsForValue().get("big-ball");
        System.out.println(user2);
        if ( user2 != null ){
            System.out.println("user : " + user2.toString() );
            return user2.toString();
        }
        return "empty " ;
    }

    @Autowired
    private UserDao userDao;
    @RequestMapping("/cache")
    public String cache() {
        System.out.println("第一次查询 1 号用户");
        System.out.println(userDao.getUser(1L));
        System.out.println("第二次查询 1 号用户");
        System.out.println(userDao.getUser(1L));
        userDao.removeFromCache(1L);// 移除缓存
        System.out.println("第三次查询 1 号用户");
        userDao.getUser(1L);// 没有缓存了

        System.out.println("第一次查询 2 号用户");
        userDao.getUser(2L);// 没有缓存了

        System.out.println("--------");
        // 测试不同的key缓存
        userDao.getUserByName("micro1");
        userDao.getUserByName(1L, "micro1");// 指定了参数name 为key 此次读取缓存

        return "OK !" ;
    }


}
