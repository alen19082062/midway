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
**1.使用Dubbo要知道服务提供者和消费者概念，而且最好调用的服务要有共同的Api，如下图是我写的入门项目。**

![Dubbo项目目录](http://wx2.sinaimg.cn/large/cf495cdcgy1fss1juop0uj20as0bj74e.jpg)

**2.配置 provider 的 application.properties**

```
#指定注册中心的位置
dubbo.registry.address = nacos://localhost:8848
#统一设置服务提供方的规则
dubbo.provider.timeout = 1000
dubbo.monitor.protocol = registry
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

## **四、运行项目**

首先启动服务提供者，在启动服务调用者，打开谷歌浏览器，输入[http://localhost:9091/sayHello/HelloWorld](http://localhost:9091/sayHello/HelloWorld)

![Dubbo浏览器显示结果](http://wx4.sinaimg.cn/large/cf495cdcgy1fss496cbz4j20hu0apdfv.jpg)

 
