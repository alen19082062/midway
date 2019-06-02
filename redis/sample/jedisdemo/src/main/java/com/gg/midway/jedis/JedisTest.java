package com.gg.midway.jedis;

import redis.clients.jedis.Jedis;

public class JedisTest {
    public static void main(String[] args)
    {
        Jedis jedis=new Jedis(RedisConf.HOST,RedisConf.PORT);
        System.out.println("jedis : " + jedis);
        // jedis.auth(RedisConf.AUTH) ;
        jedis.set("name","JedisTest");
        String value=jedis.get("name");
        System.out.println("name : " + value);
        jedis.close();
    }

}
