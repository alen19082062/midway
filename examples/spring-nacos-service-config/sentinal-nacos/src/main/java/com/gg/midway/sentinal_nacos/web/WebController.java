package com.gg.midway.sentinal_nacos.web;

//import com.alibaba.nacos.api.config.annotation.NacosValue;
//import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 创建服务
 */
@RestController
@NacosPropertySource(dataId = "web-config", autoRefreshed = true)
public class WebController {

    @Value("${server.port}")
    private Integer port;

    private static int clickCount = 0 ;
    private static int hiCount = 0 ;
    private static int confCount = 0 ;

    // 注入配置文件上下文
    @Autowired
    private ConfigurableApplicationContext applicationContext;

    /**
     * 服务接口
     * @param name
     * @return
     */
    @RequestMapping("/hello")
    public String hello(@RequestParam("name")String name) {
        String str = "block" ;
        try (Entry entry = SphU.entry("hello")) {
            // Your business logic here.
            System.out.println("hello() enter \"hello\" resources ...");
            System.out.println("hello() name : " + name );
            str = "hello ---> "+name+" port -->"+port;
            System.out.println("hello() return : " + str );
        } catch (BlockException e) {
            System.out.println("Access Resource hello block ....");
            // Handle rejected request.
            e.printStackTrace();
        }
        return str ;
    }

    /**
     * 服务接口
     * @param name
     * @return
     */
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
            System.out.println("access resource hi block ....");
            // Handle rejected request.
            e.printStackTrace();
        }
        return map ;
    }

    @NacosValue(value = "${nacos.web.propertie:123}", autoRefreshed = true)
    private String testProperties;

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
            System.out.println("conf() testProperties = "  + testProperties );
            map.put("nacos.web.propertie",testProperties);
            map.put("port",port);
            map.put("backend_conf_count",confCount);

        } catch (BlockException e) {
            // Handle rejected request.
            System.out.println("access resource conf block ....");
            e.printStackTrace();
        }
        return map ;
    }



}