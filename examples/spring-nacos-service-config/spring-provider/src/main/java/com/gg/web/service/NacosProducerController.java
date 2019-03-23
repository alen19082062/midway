package com.gg.web.service;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 创建服务
 */
@RestController
@NacosPropertySource(dataId = "web-config", autoRefreshed = true)
public class NacosProducerController {

    @Value("${server.port}")
    private Integer port;

    private static int clickCount = 0 ;

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

    @NacosValue(value = "${nacos.web.propertie:123}", autoRefreshed = true)
    private String testProperties;
    /**
     * 测试 nacos 配置参数
     * @return
     */
    @GetMapping(value = {"/conf"})
    public Map<String,Object> conf() {
        System.out.println("Running class full name : " + this.getClass().getCanonicalName());
        System.out.println("conf() testProperties = "  + testProperties );
        clickCount++ ;
        Map<String,Object> map =  new HashMap();
        map.put("nacos.web.propertie",testProperties);
        map.put("click_count",clickCount);

        return map ;
    }

    @RequestMapping("/hi/{name}")
    public String hi(@PathVariable("name") String name) {
        System.out.println("Running class full name : " + this.getClass().getCanonicalName());
        System.out.println("hi() name : " + name );
        String str = "hi ---> " + name ;
        return str ;
    }

}