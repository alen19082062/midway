package com.gg.midway.jedis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class Test02 {
    public void testJedisPool(){
        System.out.println("testJedisPool() " );

        //1 获得连接池配置对象，设置配置项
        JedisPoolConfig config = new JedisPoolConfig();
        // 最大连接数
        config.setMaxTotal(30);
        // 最大空闲连接数
        config.setMaxIdle(10);
        config.setMaxWaitMillis(3000);

        //获得连接池
        JedisPool jedisPool = new JedisPool(config,"119.29.84.103",6379);
        System.out.println("jedisPool : " + jedisPool);

        Jedis jedis=null;
        try {
            //3.获得核心对象
            jedis = jedisPool.getResource();
            System.out.println("jedis : " + jedis);

            //4.设置数据
            jedis.set("name","xinruyi");
            //5.获得数据
            String name = jedis.get("name");
            System.out.println(name);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(jedis!=null){
                jedis.close();
            }
        }

        //虚拟机关闭时，释放pool资源
        if(jedisPool!=null){
            jedisPool.close();
        }
    }

    public static void main(String[] args){
        Test02 test = new Test02();
        test.testJedisPool();
    }
}
