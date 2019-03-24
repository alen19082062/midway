package com.gg.demo;

import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
@NacosPropertySource(dataId = "web-mysql", autoRefreshed = true)
public class MybatisConfigApplication {

    public static void main(String[] args) {
        System.out.println("DemoWebApplication begin to start ");
        ApplicationContext ctx = SpringApplication.run(MybatisConfigApplication.class, args);
        System.out.println("DemoWebApplication start already ");

        //所有的bean,参考：http://412887952-qq-com.iteye.com/blog/2314051
        String[] beanNames = ctx.getBeanDefinitionNames();
        //String[] beanNames = ctx.getBeanNamesForAnnotation(RestController.class);//所有添加该注解的bean
        System.out.println("====================== " );
        System.out.println("proload beans count = " +  ctx.getBeanDefinitionCount());
        int i = 0;
        for (String str : beanNames) {
            String print_str = "" + (++i) + " : beanName:" + str ;
            System.out.println(print_str);
        }
        System.out.println("====================== " );
    }

}

