package com.gg.midway.aop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class AspectDemoApplication {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(AspectDemoApplication.class, args);
        System.out.println("Running class full name : " + AspectDemoApplication.class.getCanonicalName());

        //所有的bean,参考：http://412887952-qq-com.iteye.com/blog/2314051
        String[] beanNames = ctx.getBeanDefinitionNames();
        System.out.println("====================== ");
        System.out.println("beans count = " + ctx.getBeanDefinitionCount());
        int i = 0;
        for (String str : beanNames) {
            String print_str = "" + (++i) + " : beanName:" + str;
            // System.out.println(print_str);
        }
        System.out.println("====================== ");

        //所有添加该注解的beans
        beanNames = ctx.getBeanNamesForAnnotation(RestController.class);
        System.out.println("RestController beans count = " + beanNames.length);
        for (String str : beanNames) {
            String print_str = "" + (++i) + " : beanName:" + str;
            // System.out.println(print_str);
        }
        System.out.println("====================== ");

        beanNames = ctx.getBeanNamesForAnnotation(Service.class);
        System.out.println("Service beans count = " + beanNames.length);
        for (String str : beanNames) {
            String print_str = "" + (++i) + " : beanName:" + str;
            // System.out.println(print_str);
        }
        System.out.println("====================== ");

        beanNames = ctx.getBeanNamesForAnnotation(Configuration.class);
        System.out.println("Configuration beans count = " + beanNames.length);
        for (String str : beanNames) {
            String print_str = "" + (++i) + " : beanName:" + str;
            // System.out.println(print_str);
        }
        System.out.println("====================== ");

    }

}
