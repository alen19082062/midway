package com.gg.midway.aop.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
// @RequestMapping("/aopController")
public class AopController {

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String sayHello(String name) {
        System.out.println("sayHello() begin ... ");
        return "hello " + name;
    }
}
