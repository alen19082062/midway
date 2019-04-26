package com.gg.midway.flow_control.web;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 创建服务
 */
@RestController
public class WebController {

    @Value("${server.port}")
    private Integer port;

    private static int clickCount = 0 ;
    private static int hiCount = 0 ;
    private static int confCount = 0 ;

    // 注入配置文件上下文
    @Autowired
    private ConfigurableApplicationContext applicationContext;

    @RequestMapping("/hello")
    public String hello(@RequestParam("name")String name) {
        System.out.println("sayHello() name : " + name );
        String str = "hello ---> "+name+" port -->"+port;
        System.out.println("sayHello() return : " + str );
        return str ;
    }

    @RequestMapping("/hi/{name}")
    public Map<String,Object> hi(@PathVariable("name") String name) {
        System.out.println("hi() name : " + name );
        Map<String,Object> map =  new HashMap();

        try (Entry entry = SphU.entry("hi")) {
            // Your business logic here.
            System.out.println("conf() enter \"hi\" resources ...");
            hiCount++ ;
            map.put("backend_hi_count",hiCount);
            map.put("name",name);
            map.put("port",port);
            System.out.println("hi() port : " + port );
        } catch (BlockException e) {
            // Handle rejected request.
            map.put("desc","Blocked by flow control ...");
            map.put("ret",8001);
            System.out.println("access resource hi block ....");
            e.printStackTrace();
        }
        return map ;
    }

    /**
     * 测试 nacos 配置参数
     * @return
     */
    @GetMapping(value = {"/conf"})
    public Map<String,Object> conf() {
        System.out.println("Running class full name : " + this.getClass().getCanonicalName());
        Map<String,Object> map =  new HashMap();

        try (Entry entry = SphU.entry("conf")) {
            // Your business logic here.
            System.out.println("conf() enter \"conf\" resources ...");

            confCount++ ;
            map.put("port",port);
            map.put("backend_conf_count",confCount);

        } catch (BlockException e) {
            // Handle rejected request.
            map.put("desc","Blocked by flow control ...");
            map.put("ret",8001);
            System.out.println("access resource conf block ....");
            e.printStackTrace();
        }
        return map ;
    }
}