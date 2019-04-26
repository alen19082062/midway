package com.gg.web.service;

import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import com.gg.web.dao.User;
import com.gg.web.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 创建服务
 */
@RestController
@NacosPropertySource(dataId = "web-config", autoRefreshed = true)
public class WebController {

    @Value("${server.port}")
    private Integer port;

    private static int clickCount = 0 ;
    private static int hiCount = 0 ;
    private static int confCount = 0 ;

    // 注入配置文件上下文
    @Autowired
    private ConfigurableApplicationContext applicationContext;

//    @NacosInjected
//    private NamingService namingService;
//
//    @RequestMapping("/services")
//    @ResponseBody
//    public List<Instance> get(@RequestParam String serviceName) throws NacosException {
//        return namingService.getAllInstances(serviceName);
//    }

    /**
     * 服务接口
     * @param name
     * @return
     */
    @RequestMapping("/hello")
    public String hello(@RequestParam("name")String name) {
        System.out.println("sayHello() name : " + name );
        String str = "hello ---> "+name+" port -->"+port;
        System.out.println("sayHello() return : " + str );
        return str ;
    }

    /**
     * 服务接口
     * @param name
     * @return
     */
    @RequestMapping("/hi/{name}")
    public Map<String,Object> hi(@PathVariable("name") String name) {
        System.out.println("hi() name : " + name );

        Map<String,Object> map =  new HashMap();
        hiCount++ ;
        map.put("backend_hi_count",hiCount);
        map.put("name",name);
        map.put("port",port);
        System.out.println("hi() port : " + port );
        return map ;
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
        confCount++ ;
        Map<String,Object> map =  new HashMap();
        map.put("nacos.web.propertie",testProperties);
        map.put("port",port);
        map.put("backend_conf_count",confCount);

        return map ;
    }


    @Autowired
    UserMapper userMapper;

    @GetMapping("get/{name}")
    public List<User> getUser(@PathVariable("name") String name) {
        return userMapper.findByName(name) ;
    }

    @GetMapping("all")
    public List<User> getUser() {
        return userMapper.getAll();
    }
}