## 2019-2-12 计划  

### dubbo  + zookeeper （nacos）

+ JDK 1.8  
+ maven 3.6  
+ spring boot  2.1.2 
+ dubbo 2.6.5  或者 2.7 
+ rocket mq 4.4  消息中间件 
+ zookeeper 3.4.13 （nacos 0.8） 服务注册
+ 不使用分布式配置 （配置项开始时不会多）  
+ 负载均衡及熔断服务　open feign 

### zk .vs. nacos

nacos 不好的地方 

+ nacos 现在有些乱，版本 0.8  
+ nacos 分布式配置复杂 ， alibaba cloud 没有稳定
+ nacos 做 docker 案例不多 

好的地方  

+ nacos 图形界面，易于维护
+ zk 就 CP 类型，不是 AP 类型的  
+ zk 很久版本没有更新了 
+ zk 没有分布式配置功能 



## 2019-3-30  结论

+ 服务以 web 方式提供 

+ 使用 RocketMQ 中间件 

+ 使用 Nacos 作为服务注册、集中配置服务 

+ Sentinel 作为流控服务  

+ Redis 重要 

   

### 现网运行环境 

+ Naocs 在外网服务器 http://119.29.84.41:8848 
+ 使用 Mysql 服务器 作为持久化存储
+ Nacos 区分 Namespace ，存放多个环境的配置信息，现在有 dev-home，dev-office 
+ provider，consumer 加上流控功能，流控规则存放在 nacos 中 
+ 使用一个 rocket MQ，供所有客户端使用   
+ 使用 Redis   

### Nacos 的看法

- 1.0.0 版本接近稳定，近期会发一个稳定版本 
- 集中配置功能使用方便，支持 Namespace  
- 服务注册支持 namespace  
- 服务注册问题，provider server 在内网，nacos 在外网，进行注册 
- 服务注册问题，管理页面，服务管理项会退出 
- 使用 namespace ，区分环境很重要  



 





