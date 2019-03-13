package com.gg.demo.web;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@RestController
@NacosPropertySource(dataId = "web-config", autoRefreshed = true)
public class DemoController {

    private static int byeCount = 0 ;
    private static int confCount = 0 ;

    // 注入配置文件上下文
    @Autowired
    private ConfigurableApplicationContext applicationContext;

    @RequestMapping("/hello/{name}")
    public String hello(@PathVariable("name") String name) {
        System.out.println("Running class full name : " + this.getClass().getCanonicalName());
        System.out.println("sayHello() name : " + name );
        return "hello : " + name ;
    }

    @NacosValue(value = "${nacos.web.propertie:12344}", autoRefreshed = true)
    private String testProperties;

    @GetMapping(value = {"/conf"})
    public Map<String,Object> conf() {
        System.out.println("Running class full name : " + this.getClass().getCanonicalName());
        System.out.println("conf() testProperties = "  + testProperties );
        confCount++ ;
        Map<String,Object> map =  new HashMap();
        map.put("nacos.web.propertie",testProperties);
        map.put("click_count",confCount);

        // System.out.println("sayBye() properities : " + str );
        return map ;
    }

    @GetMapping(value = {"/hi"})
    public Map<String,String> hi() {
        System.out.println("Running class full name : " + this.getClass().getCanonicalName());
        // String value = applicationContext.getEnvironment().getProperty("user.name");
        // String value = applicationContext.getEnvironment().getgetProperty("user.name");
        Map<String,String> map = new HashMap<>();
        String str = "" ;
        byeCount++ ;
        System.out.println("sayBye() byeCount = "  + byeCount );

        LocalDateTime ldt=LocalDateTime.now();
        DateTimeFormatter format=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss SSS");
        String timeStr=ldt.format(format);
        map.put("now_time",timeStr);
        map.put("time_stamp", "" + System.currentTimeMillis()) ;
        map.put("click_count", "" + byeCount) ;

        return map ;

    }



}