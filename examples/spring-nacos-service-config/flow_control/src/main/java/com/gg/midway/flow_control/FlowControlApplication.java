package com.gg.midway.flow_control;

import com.alibaba.csp.sentinel.datasource.ReadableDataSource;
import com.alibaba.csp.sentinel.datasource.nacos.NacosDataSource;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
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

    private static final String KEY = "TestResource";
    // nacos server ip
    private static final String remoteAddress = "localhost";
    // nacos group
    private static final String groupId = "Sentinel:Demo";
    // nacos dataId
    private static final String dataId = "com.alibaba.csp.sentinel.demo.flow.rule";
    // if change to true, should be config NACOS_NAMESPACE_ID
    // private static boolean isDemoNamespace = false;
    // fill your namespace id,if you want to use namespace. for example: 0f5c7314-4983-4022-ad5a-347de1d1057d,you can get it on nacos's console
    // private static final String NACOS_NAMESPACE_ID = "${namespace}";


    private static void loadRules() {
        ReadableDataSource<String, List<FlowRule>> flowRuleDataSource = new NacosDataSource<>(remoteAddress, groupId, dataId,
                source -> JSON.parseObject(source, new TypeReference<List<FlowRule>>() {
                }));
        FlowRuleManager.register2Property(flowRuleDataSource.getProperty());
    }

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(FlowControlApplication.class, args);

        // FlowControlApplication.initFlowRules();

        loadRules();

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
