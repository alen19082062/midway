package com.gg.provider;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
//import com.alibaba.nacos.api.config.annotation.NacosValue;
//import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 * DubboProviderApplication
 * 服务提供启动类
 */
@EnableDubbo
@SpringBootApplication
// @NacosPropertySource(dataId = "test-dubbo", autoRefreshed = true)
public class DubboProviderApplication {

    //@NacosValue(value = "${nacos.test.dubbo.propertie:123}", autoRefreshed = true)
    //public static String testProperties;

    public static void main(String[] args) {
        // SpringApplication.run(DubboProviderApplication.class, args);

        ApplicationContext ctx = SpringApplication.run(DubboProviderApplication.class, args);
        //所有的bean,参考：http://412887952-qq-com.iteye.com/blog/2314051
        String[] beanNames = ctx.getBeanDefinitionNames();
        //String[] beanNames = ctx.getBeanNamesForAnnotation(RestController.class);//所有添加该注解的bean
        System.out.println("====================== " );
        System.out.println("bean count = " +  ctx.getBeanDefinitionCount());
        int i = 0;
        for (String str : beanNames) {
            String print_str = "" + (++i) + " : beanName:" + str ;
            System.out.println(print_str);
        }
        System.out.println("====================== " );

        // System.out.println("testProperties : " + testProperties );

    }
}
