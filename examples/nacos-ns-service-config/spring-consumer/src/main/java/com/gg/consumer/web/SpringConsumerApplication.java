package com.gg.consumer.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.xml.ws.Service;

/**
 * 消费者主应用
 */
@SpringBootApplication
@EnableDiscoveryClient
public class SpringConsumerApplication {

    @Autowired
    private RestTemplateBuilder builder;

    @Bean
    @LoadBalanced
    // 添加负载均衡支持，很简单，只需要在RestTemplate上添加@LoadBalanced注解，
    // 那么RestTemplate即具有负载均衡的功能,如果不加@LoadBalanced注解的话，
    // 会报java.net.UnknownHostException:springboot-h2异常，
    // 此时无法通过注册到Eureka Server上的服务名来调用服务，
    // 因为RestTemplate是无法从服务名映射到ip:port的，映射的功能是由LoadBalancerClient来实现的。
    public RestTemplate restTemplate() {
        System.out.println("restTemplate() RestTemplateBuilder.build()... ");
        return builder.build();
    }

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(SpringConsumerApplication.class, args);
        System.out.println("Running class full name : " + SpringConsumerApplication.class.getCanonicalName());

        String[] beanNames = ctx.getBeanDefinitionNames();
        System.out.println("====================== ");
        System.out.println("bean count = " + ctx.getBeanDefinitionCount());
        int i = 0;
        for (String str : beanNames) {
            String print_str = "" + (++i) + " : beanName:" + str;
            // System.out.println(print_str);
        }
        System.out.println("====================== ");
    }
}
