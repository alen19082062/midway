package com.gg.midway.config;

import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@NacosPropertySource(dataId = "web-mysql", autoRefreshed = true)
public class MybatisConfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisConfigApplication.class, args);
    }

}
