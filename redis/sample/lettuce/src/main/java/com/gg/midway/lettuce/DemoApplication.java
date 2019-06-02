package com.gg.midway.lettuce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication(scanBasePackages = {"com.gg.midway.lettuce"})
public class DemoApplication {

    public static void main(String[] args) {

        ApplicationContext ctx = SpringApplication.run(DemoApplication.class, args);
        System.out.println("Running class full name : " + DemoApplication.class.getCanonicalName());

        String[] beanNames = ctx.getBeanDefinitionNames();
        System.out.println("====================== " );
        System.out.println("bean count = " +  ctx.getBeanDefinitionCount());
        int i = 0;
        for (String str : beanNames) {
            String print_str = "" + (++i) + " : beanName:" + str ;
            System.out.println(print_str);
        }
        System.out.println("====================== " );

        // System.out.println("jedisCacheProvider : " + jedisCacheProvider );
        // jedisCacheProvider.setString("name", "spring boot jedis");
        // System.out.println(jedisCacheProvider.getString("name"));
        // Assert.assertEquals("terrylmay", jedisCacheProvider.getString("name"));

    }


}
