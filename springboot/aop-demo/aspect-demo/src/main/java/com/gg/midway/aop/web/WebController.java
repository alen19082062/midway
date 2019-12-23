package com.gg.midway.aop.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebController {

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String sayHello(String name) {
        System.out.println("sayHello() begin ... ");
        return "hello " + name;
    }
    @RequestMapping(value = "/hi", method = RequestMethod.GET)
    public String hi(String name) {
        System.out.println("hi() begin ... ");
        return "hello " + name;
    }
}
