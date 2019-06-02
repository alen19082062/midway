package com.gg.midway.lettuce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class LettuceApplication {

    public static void main(String[] args) {

        ApplicationContext ctx = SpringApplication.run(LettuceApplication.class, args);
        System.out.println("Running class full name : " + LettuceApplication.class.getCanonicalName());

        String[] beanNames = ctx.getBeanDefinitionNames();
        System.out.println("====================== " );
        System.out.println("bean count = " +  ctx.getBeanDefinitionCount());
        int i = 0;
        for (String str : beanNames) {
            String print_str = "" + (++i) + " : beanName:" + str ;
            System.out.println(print_str);
        }
        System.out.println("====================== " );

    }

}
