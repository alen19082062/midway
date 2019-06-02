package com.gg.midway.lettuce;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.stereotype.Component;

@SpringBootApplication
@EnableRedisHttpSession
public class SessionApplication {

    public static void main(String[] args) {
        System.out.println("main() start ... " );

        ApplicationContext ctx = SpringApplication.run(SessionApplication.class, args);
        System.out.println("main() Running class full name : " + SessionApplication.class.getCanonicalName());

        String[] beanNames = ctx.getBeanDefinitionNames();
        System.out.println("main() ====================== " );
        System.out.println("bean count = " +  ctx.getBeanDefinitionCount());
        int i = 0;
        for (String str : beanNames) {
            String print_str = "" + (++i) + " : beanName:" + str ;
            System.out.println(print_str);
        }
        System.out.println("main() ====================== " );

    }

    @Component
    @Order(1)
    public class Runner implements CommandLineRunner {
        @Override
        public void run(String... args) throws Exception {
            System.out.println("The OrderRunner start to initialize ...");
            System.out.println("Runner.run() The Runner start to initialize ...");
        }
    }

    @Component
    @Order(2)
    public class OrderRunner2 implements CommandLineRunner {
        @Override
        public void run(String... args) throws Exception {
            System.out.println("The OrderRunner2 start to initialize ...");
        }
    }
}
