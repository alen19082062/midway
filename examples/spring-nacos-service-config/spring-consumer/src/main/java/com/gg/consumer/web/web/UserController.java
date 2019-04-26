package com.gg.consumer.web.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * DemoConsumerController
 * 消费者控制层 USER 部分
 */
@RestController
public class UserController {

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

    @RequestMapping("/all")
    public List all() {
        System.out.println("Running class full name : " + this.getClass().getCanonicalName());
        List list = restTemplate.getForObject(
                "http://"+SERVICE_NAME+"/all" , List.class
        );
        return list ;
    }

}
