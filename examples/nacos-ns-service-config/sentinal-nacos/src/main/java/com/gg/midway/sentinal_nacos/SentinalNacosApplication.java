package com.gg.midway.sentinal_nacos;

import com.alibaba.csp.sentinel.datasource.Converter;
import com.alibaba.csp.sentinel.datasource.ReadableDataSource;
import com.alibaba.csp.sentinel.datasource.nacos.NacosDataSource;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.gg.midway.sentinal_nacos.config.ConfigRuleConstant;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class SentinalNacosApplication {

    // 本地配置规则
    private static void initFlowRules(){
        System.out.println("------ initFlowRules() ... " );

        List<FlowRule> rules = new ArrayList<>();
        // 定义了资源 HelloWorld 每秒最多只能通过 1 个请求。
        FlowRule rule = new FlowRule();
        rule.setResource("hello");
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        // Set limit QPS to 2.
        rule.setCount(1);
        rule.setLimitApp("default");
        rules.add(rule);
        // FlowRuleManager.loadRules(rules);

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

    // 从 Nacos 加载规则，可以动态更新
    private static void loadRules() {
        System.out.println("loadRules() from Nacos ... " );
        // 扩展读数据源（ReadableDataSource），规则中心统一推送，客户端通过注册监听器的方式时刻监听变化，
        // 比如使用 Nacos、Zookeeper 等配置中心。这种方式有更好的实时性和一致性保证。
        // 生产环境下一般采用 push 模式的数据源。
        ReadableDataSource<String, List<FlowRule>> flowRuleDataSource = new NacosDataSource<>(
                ConfigRuleConstant.remoteAddress, ConfigRuleConstant.groupId, ConfigRuleConstant.dataId,
                source -> JSON.parseObject(source, new TypeReference<List<FlowRule>>() {
                }));
        System.out.println("loadRules() ReadableDataSource : " + flowRuleDataSource );
        FlowRuleManager.register2Property(flowRuleDataSource.getProperty());
    }

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(SentinalNacosApplication.class, args);

        // initFlowRules();
        loadRules();

        // beans 列表
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
