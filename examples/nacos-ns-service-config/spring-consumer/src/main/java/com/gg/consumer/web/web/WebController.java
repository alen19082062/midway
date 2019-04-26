package com.gg.consumer.web.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * DemoConsumerController
 * 消费者控制层
 */
@RestController
public class WebController {
    private static int clickCount = 0 ;

    private static final String SERVICE_NAME = "spring-nacos-service-web-producer";
    @Autowired
    private DiscoveryClient discoveryClient;
    @Autowired
    private RestTemplate restTemplate;

    /**
     * 获取所有服务
     */
    @RequestMapping("/services")
    public Object services() {
        System.out.println("Get services list ... ");
        System.out.println("SERVICE_NAME : " + discoveryClient.getInstances(SERVICE_NAME) );
        return discoveryClient.getInstances(SERVICE_NAME);
    }

    @RequestMapping("/hello")
    public Map<String, Object> hello(@RequestParam("name") String name) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name",name);

        // 记录点击次数
        clickCount++ ;
        System.out.println("hello() clickCount = "  + clickCount );
        LocalDateTime ldt=LocalDateTime.now();
        DateTimeFormatter format=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss SSS");
        String timeStr=ldt.format(format);
        map.put("now_time",timeStr);
        map.put("time_stamp", "" + System.currentTimeMillis()) ;
        map.put("click_count", "" + clickCount) ;
        String callServiceResult = restTemplate.getForObject(
                "http://"+SERVICE_NAME+"/hello?name={name}", String.class,map);
        System.out.println(callServiceResult);
        System.out.println("hello() SERVICE_NAME : " + SERVICE_NAME);
        map.put("callServiceResult",callServiceResult);
        return map;
    }

    @RequestMapping("/hello2")
    public String hello2() {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(
                "http://"+SERVICE_NAME+"/hello?name=test", String.class);

        System.out.println("hello2() SERVICE_NAME : " + SERVICE_NAME);

        String body = responseEntity.getBody();
        HttpStatus statusCode = responseEntity.getStatusCode();
        int statusCodeValue = responseEntity.getStatusCodeValue();
        HttpHeaders headers = responseEntity.getHeaders();
        StringBuffer result = new StringBuffer();
        result.append("responseEntity.getBody()：").append(body).append("<hr>")
                .append("responseEntity.getStatusCode()：").append(statusCode).append("<hr>")
                .append("responseEntity.getStatusCodeValue()：").append(statusCodeValue).append("<hr>")
                .append("responseEntity.getHeaders()：").append(headers).append("<hr>");
        return result.toString();
    }

    @RequestMapping("/hi/{name}")
    public Map<String, String> hi(@PathVariable("name") String name) {
        System.out.println("Running class full name : " + this.getClass().getCanonicalName());
        System.out.println("hi() name : " + name );
        Map<String,Object> paraMap = new HashMap<>() ;
        paraMap.put("name",name);

        Map<String,String> entity = restTemplate.getForObject(
                "http://"+SERVICE_NAME+"/hi?name={name}",Map.class );
        return entity ;
    }

    @RequestMapping("/conf")
    public Map<String, Object>  conf() {
        System.out.println("Running class full name : " + this.getClass().getCanonicalName());
        System.out.println("conf() SERVICE_NAME : " + SERVICE_NAME);

        Map<String,Object> entity = restTemplate.getForObject(
                "http://"+SERVICE_NAME+"/conf",Map.class );
        return entity ;
    }
}
