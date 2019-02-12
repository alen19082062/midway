package com.gg.consumer.web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.gg.api.service.hello.DemoService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * DemoConsumerController
 * 消费者控制层
 */
@RestController
public class DemoConsumerController {

    @Reference(version = "${demo.service.version}")
    private DemoService demoService;

    @RequestMapping("/hello/{name}")
    public String sayHello(@PathVariable("name") String name) {
        System.out.println("Running class full name : " + this.getClass().getCanonicalName());
        System.out.println("sayHello() name : " + name );
        return demoService.sayHello(name);
    }

    @RequestMapping("/bye/{name}")
    public String sayBye(@PathVariable("name") String name) {
        System.out.println("Running class full name : " + this.getClass().getCanonicalName());
        System.out.println("sayBye() name : " + name );
        return demoService.sayBye(name);
    }

}
