package com.gg.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 创建服务
 */
@RestController
public class NacosProducerController {

    @Value("${server.port}")
    private Integer port;

    // 注入配置文件上下文
    @Autowired
    private ConfigurableApplicationContext applicationContext;

    /**
     * 服务接口
     * @param name
     * @return
     */
    @RequestMapping("/hello")
    public String sayHello(@RequestParam("name")String name) {
        System.out.println("sayHello() name : " + name );
        String str = "hello ---> "+name+" port -->"+port;
        System.out.println("sayHello() return : " + str );
        return str ;
    }

    @RequestMapping("/hi/{name}")
    public String hi(@PathVariable("name") String name) {
        System.out.println("Running class full name : " + this.getClass().getCanonicalName());
        System.out.println("hi() name : " + name );
        String str = "hi ---> " + name ;
        return str ;
    }

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