package com.gg.consumer.web;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * DubboConsumerApplication
 * 消费者启动类
 */
@EnableDubbo
@SpringBootApplication
public class DubboConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DubboConsumerApplication.class, args);
        System.out.println("Running class full name : " + DubboConsumerApplication.class.getCanonicalName());
    }
}
