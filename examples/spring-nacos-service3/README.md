## **一、运行工具与环境**

运行环境：JDK 8，Maven 3.6  
技术栈：SpringBoot 2.1.2、Nacos 0.9 
工具：IntelliJ IDEA、谷歌浏览器  

## 二、curl 命令 

服务注册
curl -X PUT "http://127.0.0.1:8848/nacos/v1/ns/instance?serviceName=nacos.naming.serviceName&ip=127.0.0.1&port=8080"

服务发现
curl -X GET "http://127.0.0.1:8848/nacos/v1/ns/instances?serviceName=nacos.naming.serviceName"


## **三、如何使用**

**1.POM 
    2.1.2  + Greenwich.RELEASE 
    spring-boot-starter-actuator   健康检查依赖于此包 
    
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.1.2.RELEASE</version>
    </parent>
    
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

**3.服务提供者和消费者**


**服务提供者：**

简单来说就是将上面接口的实现方法（称之为服用）注册到zookeeper上，并暴露端口供其他消费者消费。@Service里面的一些信息就是服务具体的注册地址。

**服务消费者：**

如上@Reference的直接表示远程调用，里面最重要的是url，表示要调用的地址，也就是上面服务暴露的地址。

POM.XML 增加，可以通过 http://127.0.0.1:9091/actuator/ 访问 （会增加 100 多个 bean) 
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>

 
 