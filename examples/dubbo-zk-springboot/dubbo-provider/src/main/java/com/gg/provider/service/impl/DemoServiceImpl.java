package com.gg.provider.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.gg.api.service.hello.DemoService;
import org.springframework.beans.factory.annotation.Value;

/**
 * DemoServiceImpl
 * 服务提供类
 */
@Service(version = "${demo.service.version}")
public class DemoServiceImpl implements DemoService {

    /**
     * The default value of ${dubbo.application.name} is ${spring.application.name}
     */
    @Value("${dubbo.application.name}")
    private String serviceName;
    @Value("${demo.service.version}")
    private String serviceVersion;
    @Value("${dubbo.registry.group}")
    private String serviceGroup;

    @Override
    public String sayHello(String name) {
        System.out.println("service name = " + serviceName );
        System.out.println("name = " + name );
        String str = String.format("Hello, %s ==> %s / %s / %s ",name, serviceName,serviceGroup,serviceVersion );
        return str ;
    }

    @Override
    public String sayBye(String name) {
        System.out.println("service name = " + serviceName );
        System.out.println("name = " + name );
        String str = String.format("Bye, %s ==> %s / %s / %s ",name, serviceName,serviceGroup,serviceVersion );
        return str ;
    }
}
