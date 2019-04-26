
## **一、运行工具与环境**

运行环境：JDK 8，Maven 3.6  
技术栈：SpringBoot 2.1.2、Dubbo 2.6.5、Nacos 0.8  
工具：IntelliJ IDEA、谷歌浏览器  

## **二、Provider POM 的依赖**
```maven
        <dependency>
            <groupId>com.alibaba.boot</groupId>
            <artifactId>dubbo-spring-boot-starter</artifactId>
            <version>0.2.1.RELEASE</version>
        </dependency>

        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>dubbo</artifactId>
            <version>2.6.5</version>
            <exclusions>
                <exclusion>
                    <groupId>org.springframework</groupId>
                    <artifactId>spring</artifactId>
                </exclusion>
                <exclusion>
                    <groupId>javax.servlet</groupId>
                    <artifactId>servlet-api</artifactId>
                </exclusion>
                <exclusion>
                    <groupId>log4j</groupId>
                    <artifactId>log4j</artifactId>
                </exclusion>
            </exclusions>
        </dependency>

        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>dubbo-registry-nacos</artifactId>
            <version>0.0.2</version>
        </dependency>

        <dependency>
            <groupId>com.alibaba.nacos</groupId>
            <artifactId>nacos-client</artifactId>
            <version>0.8.0</version>
        </dependency>

        <dependency>
            <groupId>io.netty</groupId>
            <artifactId>netty-all</artifactId>
            <version>4.1.32.Final</version>
        </dependency>
```

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

**共同Api：**

这是服务提供者和消费者共同调用的接口。

**服务提供者：**

简单来说就是将上面接口的实现方法（称之为服用）注册到zookeeper上，并暴露端口供其他消费者消费。  
@Service里面的一些信息就是服务具体的注册地址。

**服务消费者：**

```
        // 可以正常工作
        FlowRule rule2 = new FlowRule();
        rule2.setResource("com.gg.api.service.hello.HelloService:hi(java.lang.String)");
        rule2.setGrade(RuleConstant.FLOW_GRADE_QPS);
        rule2.setCount(1);
        rule2.setLimitApp("default");
        FlowRuleManager.loadRules(Collections.singletonList(rule2)); 
```

## **四、运行项目**

首先启动服务提供者，在启动服务调用者，打开谷歌浏览器，输入[http://localhost:9091/hello/allen](http://localhost:9091/hello/allen)  

启动控制台 
D:\dev\sentinel>java -Dserver.port=8080 -Dcsp.sentinel.dashboard.server=localhost:8080 -Dproject.name=sentinel-dashboard -jar sentinel-dashboard-1.4.1.jar   
