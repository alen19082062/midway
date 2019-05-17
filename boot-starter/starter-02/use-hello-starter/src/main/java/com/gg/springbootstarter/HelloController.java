package com.gg.springbootstarter;

import com.gg.starter.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@RestController
public class HelloController {
    private static int byeCount = 0 ;

    @Autowired
    HelloService helloService;

    @GetMapping("/")
    public String index(){
        System.out.println("index()  "   );
        String str = helloService.hello() ;
        System.out.println("index() str : "  + str  );
        return str ;
    }

    @GetMapping(value = {"/hi"})
    public Map<String,String> hi() {
        System.out.println("Running class full name : " + this.getClass().getCanonicalName());
        // String value = applicationContext.getEnvironment().getProperty("user.name");
        // String value = applicationContext.getEnvironment().getgetProperty("user.name");
        Map<String,String> map = new HashMap<>();
        String str = "" ;
        byeCount++ ;
        System.out.println("hi() byeCount = "  + byeCount );

        LocalDateTime ldt=LocalDateTime.now();
        DateTimeFormatter format=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss SSS");
        String timeStr=ldt.format(format);
        map.put("now_time",timeStr);
        map.put("time_stamp", "" + System.currentTimeMillis()) ;
        map.put("click_count", "" + byeCount) ;

        return map ;

    }



}