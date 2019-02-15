package com.gg.provider.service.impl;

import com.alibaba.dubbo.common.Version;
import com.alibaba.dubbo.config.annotation.Service;
import com.gg.api.service.hello.HelloService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootVersion;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

/**
 * DemoServiceImpl
 * 服务提供类
 */
@Service(version = "${demo.service.version}")
public class HelloServiceImpl implements HelloService {

    /**
     * The default value of ${dubbo.application.name} is ${spring.application.name}
     */
    @Value("${dubbo.application.name}")
    private String serviceName;
    @Value("${demo.service.version}")
    private String serviceVersion;
    //@Value("${dubbo.registry.group}")
    private String serviceGroup = "un_set";

    // @Value("${dubbo.version}")
    private String dubboVersion;
    private String springBootVersion ;
    private String nacosVersion ;

    // hi 方法调用的次数
    private static int  hiCount ;

    @Override
    public String sayHello(String name) {
        System.out.println("service name = " + serviceName );
        System.out.println("name = " + name );
        LocalDateTime ldt=LocalDateTime.now();
        DateTimeFormatter format=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss SSS");
        String timeStr=ldt.format(format);
        String str = String.format("[%S] Hello, %s ==> %s / %s / %s ",timeStr, name, serviceName,serviceGroup,serviceVersion );
        return str ;
    }

    @Override
    public String sayBye(String name) {
        System.out.println("service name = " + serviceName );
        System.out.println("name = " + name );
        LocalDateTime ldt=LocalDateTime.now();
        DateTimeFormatter format=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss SSS");
        String timeStr=ldt.format(format);
        String str = String.format("[%S] Bye, %s ==> %s / %s / %s ",timeStr, name, serviceName,serviceGroup,serviceVersion );
        return str ;
    }

    @Override
    public Map<String,String> hi(String name) {
        System.out.println("service name = " + serviceName );
        System.out.println("name = " + name );
        hiCount++ ;

        dubboVersion = Version.getVersion();
        springBootVersion = SpringBootVersion.getVersion();
        nacosVersion = "0.8.0";

        LocalDateTime ldt=LocalDateTime.now();
        DateTimeFormatter format=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss SSS");
        String timeStr=ldt.format(format);
        Map<String,String> map = new HashMap();
        map.put("now_time",timeStr);
        map.put("name",name);
        map.put("dubbo_version", dubboVersion) ;
        map.put("springboot_version", springBootVersion) ;
        map.put("nacos_version", nacosVersion) ;
        map.put("hi_count", "" + hiCount) ;
        map.put("service_name", serviceName) ;
        map.put("service_version", serviceVersion) ;
        map.put("service_group", serviceGroup) ;
        map.put("time_stamp", "" + System.currentTimeMillis()) ;
        return map ;
    }
}
