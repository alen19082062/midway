package com.gg.api.service.hello;

import java.util.Map;

/**
 * DemoService
 * 服务Api接口类
 */
public interface HelloService {

    String sayHello(String name);
    String sayBye(String name);
    Map<String,String> hi(String name);
}
