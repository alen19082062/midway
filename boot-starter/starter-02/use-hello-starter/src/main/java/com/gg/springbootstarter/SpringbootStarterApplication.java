package com.gg.springbootstarter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringbootStarterApplication {

    public static void main(String[] args) {
        System.out.println("SpringbootStarterApplication begin ...");
        ApplicationContext ctx = SpringApplication.run(SpringbootStarterApplication.class, args);
        System.out.println("SpringbootStarterApplication start already ... ");

        String[] beanNames = ctx.getBeanDefinitionNames();
        System.out.println("====================== " );

        System.out.println("proload beans count = " +  ctx.getBeanDefinitionCount());
        int i = 0;
        for (String str : beanNames) {
            String print_str = "" + (++i) + " : beanName:" + str ;
            System.out.println(print_str);
        }
        System.out.println("====================== " );

        Logger logger =  LoggerFactory.getLogger(Object.class);
        logger.trace("trace ==== ");
        logger.debug("debug ==== ");
        logger.info("info ==== ");
        logger.warn("warn ==== ");
    }
}