package com.gg.demo_web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoWebApplication {

    public static void main(String[] args) {
        System.out.println("DemoWebApplication begin to start ");
        SpringApplication.run(DemoWebApplication.class, args);
        System.out.println("DemoWebApplication start already ");
    }

}

