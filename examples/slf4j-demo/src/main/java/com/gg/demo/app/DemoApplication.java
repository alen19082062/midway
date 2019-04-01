package com.gg.demo.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        System.out.println("The demo app to start.");
        SpringApplication.run(DemoApplication.class, args);
        System.out.println("The demo app started already .....");
    }

}

