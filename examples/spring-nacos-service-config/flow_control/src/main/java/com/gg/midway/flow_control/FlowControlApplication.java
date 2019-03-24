package com.gg.midway.flow_control;

import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class FlowControlApplication {

    private static void initFlowRules(){
        System.out.println("------ initFlowRules() ... " );

        List<FlowRule> rules = new ArrayList<>();
        // 定义了资源 HelloWorld 每秒最多只能通过 1 个请求。
        // 无法正常工作

//        FlowRule rule = new FlowRule();
//        rule.setResource("hello");
//        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
//        // Set limit QPS to 2.
//        rule.setCount(1);
//        rule.setLimitApp("default");
//        rules.add(rule);
//        // FlowRuleManager.loadRules(rules);

        // 流控 hi
        FlowRule rule2 = new FlowRule();
        rule2.setResource("hi");
        rule2.setGrade(RuleConstant.FLOW_GRADE_QPS);
        rule2.setCount(1);
        rule2.setLimitApp("default");
        rules.add(rule2);

        // 流控 conf 资源
        FlowRule rule3 = new FlowRule();
        rule3.setResource("conf");
        rule3.setGrade(RuleConstant.FLOW_GRADE_QPS);
        rule3.setCount(1);
        rule3.setLimitApp("default");
        rules.add(rule3);

        FlowRuleManager.loadRules(rules);

    }


    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(FlowControlApplication.class, args);

        FlowControlApplication.initFlowRules();

        //所有的bean,参考：http://412887952-qq-com.iteye.com/blog/2314051
        String[] beanNames = ctx.getBeanDefinitionNames();
        System.out.println("====================== " );
        System.out.println("bean count = " +  ctx.getBeanDefinitionCount());
        int i = 0;
        for (String str : beanNames) {
            String print_str = "" + (++i) + " : beanName:" + str ;
            // System.out.println(print_str);
        }
        System.out.println("====================== " );
    }

}
