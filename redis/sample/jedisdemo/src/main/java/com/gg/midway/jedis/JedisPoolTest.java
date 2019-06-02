package com.gg.midway.jedis;

import redis.clients.jedis.*;

public class JedisPoolTest {
    public void testJedisPool(){
        System.out.println("testJedisPool() " );

        //1 获得连接池配置对象，设置配置项
        JedisPoolConfig config = new JedisPoolConfig();
        // 最大连接数
        config.setMaxTotal(30);
        // 最大空闲连接数
        config.setMaxIdle(10);
        config.setMaxWaitMillis(3000);

//        JedisShardInfo shardInfo = new JedisShardInfo(HOST, PORT);
//        shardInfo.setPassword(AUTH);
//        List<JedisShardInfo> list = new LinkedList<JedisShardInfo>();
//        list.add(shardInfo);
//        ShardedJedisPool pool = new ShardedJedisPool(config, list);

        JedisPool pool = new JedisPool(config,RedisConf.HOST,RedisConf.PORT);
        System.out.println("Jedis pool : " + pool);
        Jedis jedis=null;
        try {
            jedis = pool.getResource();
            jedis.auth(RedisConf.AUTH);
            System.out.println("jedis : " + jedis);

            // 设置数据
            jedis.set("name","JedisPoolTest");
            // 获得数据
            String name = jedis.get("name");
            System.out.println("name : " +  name);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(jedis!=null){
                jedis.close();
            }
        }

        //虚拟机关闭时，释放pool资源
        if(pool!=null){
            pool.close();
        }
    }

    public static void main(String[] args){
        JedisPoolTest test = new JedisPoolTest();
        test.testJedisPool();
    }
}
