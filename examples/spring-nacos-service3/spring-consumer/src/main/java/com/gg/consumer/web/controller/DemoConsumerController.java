package com.gg.consumer.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

/**
 * DemoConsumerController
 * 消费者控制层
 */
@RestController
public class DemoConsumerController {

    //@Reference(version = "${demo.service.version}")
    //private HelloService demoService;

    private static int clickCount = 0 ;

    private static final String SERVICE_NAME = "spring-nacos-service-web-producer";
    private static final String HOST = "192.168.10.116";

    // 注入配置文件上下文
    @Autowired
    private ConfigurableApplicationContext applicationContext;

    @Autowired
    private DiscoveryClient discoveryClient;

    // @Autowired
    private LoadBalancerClient loadBalancerClient;
    @Autowired
    private RestTemplate restTemplate;

    /**
     * 获取所有服务
     */
    @RequestMapping("/services")
    public Object services() {
        System.out.println("Get services list ... ");
        System.out.println("SERVICE_NAME : " + discoveryClient.getInstances(SERVICE_NAME) );
        return discoveryClient.getInstances(SERVICE_NAME);
    }

    /**
     * 获取所有服务
     */
    @RequestMapping("/callSayHello")
    public Map<String, Object> services(@RequestParam("name") String name) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name",name);

        // 记录点击次数
        clickCount++ ;
        System.out.println("sayBye() clickCount = "  + clickCount );
        LocalDateTime ldt=LocalDateTime.now();
        DateTimeFormatter format=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss SSS");
        String timeStr=ldt.format(format);
        map.put("now_time",timeStr);
        map.put("time_stamp", "" + System.currentTimeMillis()) ;
        map.put("click_count", "" + clickCount) ;

        String callServiceResult = restTemplate.getForObject("http://"+SERVICE_NAME+"/hello?name={name}", String.class,map);
        System.out.println(callServiceResult);

        map.put("callServiceResult",callServiceResult);

        return map;

    }

    // =============================== 以下是以前的服务

    @RequestMapping("/bye/{name}")
    public String sayBye(@PathVariable("name") String name) {
        System.out.println("Running class full name : " + this.getClass().getCanonicalName());
        // String value = applicationContext.getEnvironment().getProperty("user.name");
        // String value = applicationContext.getEnvironment().getgetProperty("user.name");
        Map<String,Object> map = applicationContext.getEnvironment().getSystemProperties();
        String str = map.toString();
        System.out.println("sayBye() name : " + name );
        System.out.println("sayBye() properities : " + str );

        return "" ;
    }

    @RequestMapping("/hi/{name}")
    public Map<String, String> hi(@PathVariable("name") String name) {
        System.out.println("Running class full name : " + this.getClass().getCanonicalName());
        System.out.println("hi() name : " + name );
        Map<String,String> map = new HashMap<>() ;
        return map ;
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
