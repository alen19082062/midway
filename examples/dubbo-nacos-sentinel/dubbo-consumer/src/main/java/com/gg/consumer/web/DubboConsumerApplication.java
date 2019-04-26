package com.gg.consumer.web;

import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * DubboConsumerApplication
 * 消费者启动类
 */
@EnableDubbo
@SpringBootApplication
public class DubboConsumerApplication {

    private static void initFlowRules(){
        System.out.println("====================== initFlowRules() " );

        // 定义了资源 HelloWorld 每秒最多只能通过 1 个请求。
        // 无法正常工作
        List<FlowRule> rules = new ArrayList<>();

        FlowRule rule = new FlowRule();
        rule.setResource("hello");
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        // Set limit QPS to 2.
        rule.setCount(1);
        rule.setLimitApp("default");
        rules.add(rule);
        // FlowRuleManager.loadRules(rules);

        // dubbo 调用流控 ，每次需要 new 新的 FlowRule 对象
        FlowRule rule2 = new FlowRule();
        rule2.setResource("com.gg.api.service.hello.HelloService:sayBye(java.lang.String)");
        rule2.setGrade(RuleConstant.FLOW_GRADE_QPS);
        rule2.setCount(1);
        rule2.setLimitApp("default");
        rules.add(rule2);

        // 可以正常工作
        // List<FlowRule> rules = new ArrayList<>();
        FlowRule rule3 = new FlowRule();
        rule3.setResource("com.gg.api.service.hello.HelloService:hi(java.lang.String)");
        rule3.setGrade(RuleConstant.FLOW_GRADE_QPS);
        rule3.setCount(1);
        rule3.setLimitApp("default");
        rules.add(rule3);

        FlowRuleManager.loadRules(rules);

    }

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

        DubboConsumerApplication.initFlowRules();

    }


}
