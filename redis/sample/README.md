
jedisdemo ：直接使用 jedis 连接

lettuce : 使用 spring-boot 2.0 内置的 redis 连接功能
有连接池，可以加密码，密码 2000@cylife  
1、可配置 <string,string> 类型，
2、可以配置 <string,object> 类型，存放 user 对象

JacksonJsonRedisSerializer：jackson-json工具提供了javabean与json之间
的转换能力，可以将pojo实例序列化成json格式存储在redis中，也可以将
json格式的数据转换成pojo实例。因为jackson工具在序列化和反序列化时，
需要明确指定Class类型，因此此策略封装起来稍微复杂。
【需要jackson-mapper-asl工具支持】
 
StringRedisSerializer：Key或者value为字符串的场景，根据指定的charset
对数据的字节序列编码成string，是“new String(bytes, charset)”和
“string.getBytes(charset)”的直接封装。是最轻量级和高效的策略 
 