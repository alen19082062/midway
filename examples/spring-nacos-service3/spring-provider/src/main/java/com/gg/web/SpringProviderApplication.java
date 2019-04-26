package com.gg.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ApplicationContext;

import java.util.Scanner;

/**
 * 开启服务发现
 */


@SpringBootApplication
@EnableDiscoveryClient
public class SpringProviderApplication {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String port = scanner.nextLine();
        System.out.println("port : " + port );
        new SpringApplicationBuilder(SpringProviderApplication.class)
                .properties("server.port=" + port).run(args);

        /*
        ApplicationContext ctx = SpringApplication.run(SpringProviderApplication.class, args);

        //所有的bean,参考：http://412887952-qq-com.iteye.com/blog/2314051
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
         */
    }

}

