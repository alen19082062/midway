package com.gg.midway.sentinal_nacos;

import com.alibaba.csp.sentinel.datasource.Converter;
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
public class SentinalNacosApplication {

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
        System.out.println("loadRules() from Nacos ... " );
        ReadableDataSource<String, List<FlowRule>> flowRuleDataSource = new NacosDataSource<>(remoteAddress, groupId, dataId,
                source -> JSON.parseObject(source, new TypeReference<List<FlowRule>>() {
                }));
        FlowRuleManager.register2Property(flowRuleDataSource.getProperty());
    }


    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(SentinalNacosApplication.class, args);

        // SentinalNacosApplication.initFlowRules();
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
