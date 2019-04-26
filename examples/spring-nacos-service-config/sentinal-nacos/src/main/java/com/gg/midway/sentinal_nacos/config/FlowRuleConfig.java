package com.gg.midway.sentinal_nacos.config;

import java.util.Properties;
import java.util.concurrent.Executor;
import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.PropertyKeyConst;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;

/**
 * Nacos Server 上的例子，演示了 config 的各个功能
 */
public class FlowRuleConfig {

    public static void main(String[] args) throws NacosException, InterruptedException {
        String serverAddr = "localhost";
        String dataId = "flow-rule";
        String group = "DEFAULT_GROUP";
        Properties properties = new Properties();
        properties.put(PropertyKeyConst.SERVER_ADDR, serverAddr);
        ConfigService configService = NacosFactory.createConfigService(properties);

        String content = configService.getConfig(dataId, group, 5000);
        System.out.println("configService.getConfig() content : " + content);
        configService.addListener(dataId, group, new Listener() {
            @Override
            public void receiveConfigInfo(String configInfo) {
                System.out.println("Listener.receiveConfigInfo() : " + configInfo);
            }

            @Override
            public Executor getExecutor() {
                System.out.println("FlowRuleConfig getExecutor()  " );
                return null;
            }
        });

        boolean isPublishOk = configService.publishConfig(dataId, group, "content");
        System.out.println("configService.publishConfig() ret = " + isPublishOk);

        Thread.sleep(3000);
        System.out.println("getConfig() again ... " );
        content = configService.getConfig(dataId, group, 5000);
        System.out.println("flow-rule content : " + content);

        // remove config
        boolean isRemoveOk = configService.removeConfig(dataId, group);
        System.out.println("configService.removeConfig() ret = " + isPublishOk);
        System.out.println(isRemoveOk);
        Thread.sleep(3000);

        System.out.println("getConfig()  ... " );
        content = configService.getConfig(dataId, group, 5000);
        System.out.println("flow-rule content : " + content);
        Thread.sleep(300000);

    }
}
