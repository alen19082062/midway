package com.gg.web;

import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ApplicationContext;

import java.util.Scanner;

/**
 * 开启服务发现
 */
@SpringBootApplication
@EnableDiscoveryClient
@NacosPropertySource(dataId = "web-mysql", autoRefreshed = true)
public class SpringProviderApplication {

    public static void main(String[] args) {

        ApplicationContext ctx = SpringApplication.run(SpringProviderApplication.class, args);

        String[] beanNames = ctx.getBeanDefinitionNames();
        //String[] beanNames = ctx.getBeanNamesForAnnotation(RestController.class);//所有添加该注解的bean
        System.out.println("====================== " );
        System.out.println("bean count = " +  ctx.getBeanDefinitionCount());
        int i = 0;
        for (String str : beanNames) {
            String print_str = "" + (++i) + " : beanName:" + str ;
            // System.out.println(print_str);
        }
        System.out.println("====================== " );

    }

}

