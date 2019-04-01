package com.gg.demo.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/")
public class DemoController {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private HelloService helloService;

    @RequestMapping("/auto")
    public String home(){
        return helloService.say();
    }

    @RequestMapping("/auto2")
    public String changeHome(){
        helloService.setMsg("world , changed !");
        return helloService.say();
    }

    @RequestMapping
    public String hello() {
        String str = "Level 1 : Hello Spring-Boot" ;
        str += "<p> http://127.0.0.1:8080/ok" ;
        str += "<p> http://127.0.0.1:8080/info?name=xxxx" ;
        str += "<p> http://127.0.0.1:8080/list" ;
        str += "<p> http://127.0.0.1:8080/log" ;
        str += "<p> http://127.0.0.1:8080/auto" ;

        return str ;
    }

    @GetMapping("/log")
    public String logging(){

        // 级别由低到高 trace<debug<info<warn<error
        logger.trace("这是一个trace日志...");
        logger.debug("这是一个debug日志...");
        // SpringBoot默认是info级别，只会输出info及以上级别的日志
        logger.info("这是一个info日志...");
        logger.warn("这是一个warn日志...");
        logger.error("这是一个error日志...");

        return "log ... ";
    }

    @RequestMapping("/ok")
    public String getInfo() {
        return "Level 2 : OK ";
    }

    @RequestMapping("/user")
    public String getUser(@RequestParam Map map) {
        String str = map.toString();
        System.out.println(str );
        return str;
    }

    @RequestMapping("/info")
    public Map<String, String> getInfo(@RequestParam String name) {
        System.out.println("name = " + name );
        Map<String, String> map = new HashMap<>();
        map.put("name", name);
        return map;
    }

    @RequestMapping("/list")
    public List<Map<String, String>> getList() {
        List<Map<String, String>> list = new ArrayList<>();
        Map<String, String> map = null;
        map = new HashMap<>();
        map.put("name", "张三");
        map.put("gender", "男");
        list.add(map);

        map.put("name", "李四");
        map.put("gender", "女");
        list.add(map);

        map.put("name", "王五");
        map.put("gender", "男");
        list.add(map);

        return list;
    }
}
