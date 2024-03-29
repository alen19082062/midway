﻿## **一、运行工具与环境**

运行环境：JDK 8，Maven 3.6  
技术栈：SpringBoot 2.1.2、Nacos 0.9 
工具：IntelliJ IDEA、谷歌浏览器  

## 二、curl 命令 

服务注册
curl -X PUT "http://127.0.0.1:8848/nacos/v1/ns/instance?serviceName=nacos.naming.serviceName&ip=127.0.0.1&port=8080"

服务发现
curl -X GET "http://127.0.0.1:8848/nacos/v1/ns/instances?serviceName=nacos.naming.serviceName"


## **三、如何使用**

**1、POM <dependencyManagement> 是关键，springboot 为 
    2.1.2  + Greenwich.RELEASE 
    spring-cloud-starter-alibaba-nacos-discovery 0.2.1 对应的 nacos 为 0.6.2 
    spring-boot-starter-actuator   健康检查依赖于此包 
    
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.1.2.RELEASE</version>
    </parent>
    
    <dependencies>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>
            <version>0.2.1.RELEASE</version>
        </dependency>
    </dependencies>
   
    <dependencyManagement>
        <dependencies>
            <dependency>
                <groupId>org.springframework.cloud</groupId>
                <artifactId>spring-cloud-dependencies</artifactId>
                <version>Greenwich.RELEASE</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
        </dependencies>
    </dependencyManagement> 
 
**2.配置 provider 的 application.properties**

```
#指定当前服务/应用的名字（同样的服务名字相同，不要和别的服务同名）
spring.application.name = spring-nacos-service-web-producer
server.port = 9009
# Nacos服务的地址
spring.cloud.nacos.discovery.server-addr=127.0.0.1:8848
management.endpoints.web.exposure.include=*
```

**3、配置 consumer 的 application.properties**

``` 
spring.application.name = spring-nacos-service-web-consumer
server.port = 9109
# Nacos服务的地址
spring.cloud.nacos.discovery.server-addr=127.0.0.1:8848

management.endpoints.web.exposure.include=*

```

**3.服务提供者和消费者**


**4.服务提供者：负载均衡的方法**
1、修改 edit configuration ，选中 all parallel run 
2、启动一个 SpringProviderApplication ，端口是默认的  9009  
3、修改 application.properties 文件，端口改为 9010，再次启动
4、查询服务列表， http://localhost:9109/services ，可以看到启动了两个服务 
5、调用服务 http://localhost:9109/callSayHello?name=abc ，可以看到返回的接口在变化 
   负载均衡生效了   
 
 
 