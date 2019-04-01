package com.gg.demo.app;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(2)
public class CMD_Runner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println("The MD Runner start to initialize ...");
    }
}