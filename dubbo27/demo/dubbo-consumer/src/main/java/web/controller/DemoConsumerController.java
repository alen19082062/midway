package web.controller;

import com.gg.api.service.hello.HelloService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.apache.dubbo.monitor.MonitorService;

import java.util.Map;

/**
 * DemoConsumerController
 * 消费者控制层
 */
@RestController
public class DemoConsumerController {

    @Reference(version = "${demo.service.version}")
    // @Reference
    private HelloService demoService;

    private static int byeCount = 0 ;

    @RequestMapping("/hello/{name}")
    public String sayHello(@PathVariable("name") String name) {
        System.out.println("Running class full name : " + this.getClass().getCanonicalName());
        System.out.println("sayHello() name : " + name );
        return demoService.sayHello(name);
    }


    @RequestMapping("/hi/{name}")
    public Map<String, String> hi(@PathVariable("name") String name) {
        System.out.println("demoService : " + demoService);
        System.out.println("Running class full name : " + this.getClass().getCanonicalName());
        System.out.println("hi() name : " + name );
        return demoService.hi(name);
    }



}
