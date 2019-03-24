##  ** 功能描述  **
使用 Nacos 作为 服务注册中心和配置中心 
客户端使用 openfeign ，有负载均衡功能 
服务端使用 iBatis 连接数据库   
\resources\user.sql 创建表文件 

模块功能描述 
【simple-config】 
 从Nacos server取通用的配置参数 

【mybatis-config】
1、从Nacos server取通用的配置参数，
2、从Nacos server取mysql配置参数，
3、使用mybatis+连接池，对mysql数据库查询 

【spring-consumer】 
 从Nacos server发现服务，调用 spring-provider 提供的服务 
 
【spring-provider】 
 1、从Nacos server取通用的配置参数，
 2、从Nacos server取mysql配置参数，
 3、使用mybatis+连接池，对mysql数据库查询 
 4、对外提供 服务接口 

## **一、运行工具与环境**

运行环境：JDK 8，Maven 3.6  
依赖库：SpringBoot 2.1.2  Greenwich.RELEASE (2.1.1会有问题） Nacos 0.9 

## 二、curl 命令 

服务注册
curl -X PUT "http://127.0.0.1:8848/nacos/v1/ns/instance?serviceName=nacos.naming.serviceName&ip=127.0.0.1&port=8080"

服务发现
curl -X GET "http://127.0.0.1:8848/nacos/v1/ns/instances?serviceName=nacos.naming.serviceName"

JDBC 的配置，存放在 nacos 中 ，名字 web-mysql.properties 

JDBC 内容：
spring.datasource.username= root
spring.datasource.password= 1234
spring.datasource.url= jdbc:mysql://localhost:3306/spring?useUnicode=true&characterEncoding=utf-8&useSSL=true&serverTimezone=UTC
spring.datasource.driver-class-name= com.mysql.cj.jdbc.Driver

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
# Nacos config address
nacos.config.server-addr=127.0.0.1:8848

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

数据库查询 
http://localhost:9000/all

1、修改 edit configuration ，选中 all parallel run 
2、启动一个 SpringProviderApplication ，端口是默认的  9009  
3、修改 application.properties 文件，端口改为 9010，再次启动
4、查询服务列表， http://localhost:9109/services ，可以看到启动了两个服务 
5、调用服务 http://localhost:9109/callSayHello?name=abc ，可以看到返回的接口在变化 
   负载均衡生效了   
 
 
FAQ 
1、错误：Failed to configure a DataSource: 'url' attribute is not specified and no 
   embedded datasource could be configured.
   检查  @NacosPropertySource(dataId = "web-mysql", autoRefreshed = true) 这个有没有
   配置好 
   
   