package com.gg.consumer.web;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Collections;

/**
 * DubboConsumerApplication
 * 消费者启动类
 */
@EnableDubbo
@SpringBootApplication
public class DubboConsumerApplication {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(DubboConsumerApplication.class, args);
        System.out.println("Running class full name : " + DubboConsumerApplication.class.getCanonicalName());

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
    }

    FlowRule flowRule = new FlowRule();
		flowRule.setResource(
                "org.springframework.cloud.alibaba.cloud.examples.FooService:hello(java.lang.String)");
		flowRule.setCount(10);
		flowRule.setGrade(RuleConstant.FLOW_GRADE_QPS);
		flowRule.setLimitApp("default");
		FlowRuleManager.loadRules(Collections.singletonList(flowRule));
}
