package com.gg.midway.lettuce.web;

import com.gg.midway.lettuce.dao.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Controller
 * 消费者控制层
 */
@RestController
public class Controller {

    @Autowired(required = false)
    private StringRedisTemplate stringRedisTemplate;

    //@RequestMapping(value = "/index", method = RequestMethod.POST)
    //@ResponseBody
    @RequestMapping("/session")
    public User session(HttpServletRequest request) {
        System.out.println("session() begin ...");
        System.out.println("session() " + request.getServerName() + ":" + request.getServerPort() );
        HttpSession session = request.getSession();
        String sessionID = session.getId() ;
        System.out.println("session() session ID : " + sessionID );

        if (session.getAttribute("user") == null) {
            session.setAttribute("user", "zhangsan");
            System.out.println("session() session ID 不存在");
        } else {
            System.out.println("session() session 存在");
        }
        User user=new User();
        user.setUserId(6545);
        user.setUserName("big-ball");
        user.setPassword("22222");
        user.setPhone("13900001111");

        // 设置到 redis 中去
        if(!redisTemplate.hasKey(sessionID)) {
            System.out.println("session() set user info to session " + sessionID );
            redisTemplate.opsForValue().set(sessionID,user);
            System.out.println("session() user set ok !");
        }
        return user ;
    }

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

    // 注入配置文件上下文
    @Autowired
    private ConfigurableApplicationContext applicationContext;

    @RequestMapping("/conf")
    public Map<String, Object>  conf() {
        System.out.println("Running class full name : " + this.getClass().getCanonicalName());
        // String value = applicationContext.getEnvironment().getProperty("user.name");
        // String value = applicationContext.getEnvironment().getgetProperty("user.name");
        Map<String,Object> map = applicationContext.getEnvironment().getSystemProperties();
        String str = map.toString();
        System.out.println("conf() properities : " + str );
        return map ;
    }

}
