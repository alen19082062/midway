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





