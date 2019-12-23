package com.gg.midway.cache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;

@EnableCaching
@SpringBootApplication
public class CacheApplication {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(CacheApplication.class, args);
        System.out.println("Running class full name : " + CacheApplication.class.getCanonicalName());

        String[] beanNames = ctx.getBeanDefinitionNames();
        System.out.println("====================== " );
        System.out.println("bean count = " +  ctx.getBeanDefinitionCount());
        int i = 0;
        for (String str : beanNames) {
            String print_str = "" + (++i) + " : " + str ;
            System.out.println(print_str);
        }
        System.out.println("====================== " );
    }

}
