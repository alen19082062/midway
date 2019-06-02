package com.gg.midway.jedis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.ResourceBundle;

public class Test {
    // private JedisUtil() {}
    private static JedisPool jedisPool;
    private static int maxtotal = 100 ;
    private static int maxwaitmillis = 3000;
    private static String host = "119.29.84.103";
    private static int port = 6379;

    /*读取jedis.properties配置文件*/
//    static{
//        ResourceBundle rb = ResourceBundle.getBundle("jedis");
//        maxtotal = Integer.parseInt(rb.getString("maxtotal"));
//        maxwaitmillis = Integer.parseInt(rb.getString("maxwaitmillis"));
//        host = rb.getString("host");
//        port = Integer.parseInt(rb.getString("port"));
//    }


    /*创建连接池*/
    static{
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(maxtotal);
        jedisPoolConfig.setMaxWaitMillis(maxwaitmillis);
        jedisPool = new JedisPool(jedisPoolConfig,host,port);
    }

    /*获取jedis*/
    public static Jedis getJedis(){
        return jedisPool.getResource();
    }

    /*关闭Jedis*/
    public static void close(Jedis jedis){
        if(jedis!=null){
            jedis.close();
        }
    }

    public static void main(String[] args){
        Test test = new Test();

    }
}
