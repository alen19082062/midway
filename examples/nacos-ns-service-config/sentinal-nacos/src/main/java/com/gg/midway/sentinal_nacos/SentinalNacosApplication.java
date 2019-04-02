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

    // 本地加载
    private static void initFlowRules(){
        System.out.println("------ initFlowRules() ... " );

//        List<FlowRule> rules = new ArrayList<>();
//        // 流控 conf 资源
//        FlowRule rule3 = new FlowRule();
//        rule3.setResource("conf");
//        rule3.setGrade(RuleConstant.FLOW_GRADE_QPS);
//        rule3.setCount(1);
//        rule3.setLimitApp("default");
//        rules.add(rule3);
//
//        FlowRuleManager.loadRules(rules);

        /*
        {
            resource: hello,
            controlBehavior: 0,
            count: 1.0,
            grade: 1,
            limitApp: default,
            strategy: 0
        }
        */
        String remoteAddress = "localhost";
        String groupId = "DEFAULT_GROUP";
        String dataId = "flow-control";
        Converter<String, List<FlowRule>> parser = source -> JSON.parseObject(source,new TypeReference<List<FlowRule>>() {});
        ReadableDataSource<String, List<FlowRule>> nacosDataSource = new NacosDataSource<>(remoteAddress, groupId, dataId, parser);
        FlowRuleManager.register2Property(nacosDataSource.getProperty());

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
