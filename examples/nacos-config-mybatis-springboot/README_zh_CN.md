# Nacos-config-mybatis-springboot

如标题 

## 在本地运行这个测试例子
1. 不使用 xml 配置， https://zhuanlan.zhihu.com/p/25010714?refer=dreawer 

2. JDBC 配置信息放在 nacos 中

4. 编译并运行测试例子

```
# 打开一个终端窗口，编译并启动发送端
cd rocketmq-produce-demo
mvn clean package
java -jar target/rocketmq-produce-demo-0.0.1-SNAPSHOT.jar

# 打开另一个终端窗口，编译并启动消费端
cd rocketmq-consume-demo
mvn clean package
java -jar target/rocketmq-consume-demo-0.0.1-SNAPSHOT.jar
```
结合测试代码，观察窗口中消息的发送和接收情况
