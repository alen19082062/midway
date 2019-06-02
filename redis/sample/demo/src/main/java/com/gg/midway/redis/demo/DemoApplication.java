package com.gg.midway.redis.demo;

import com.gg.midway.redis.demo.config.ClusterRedisConfig;
import com.gg.midway.redis.demo.config.StandaloneRedisConfig;
import com.gg.midway.redis.demo.config.StandaloneRedisConfig;

import com.gg.midway.redis.demo.provider.ICacheProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.Assert;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Arrays;

@SpringBootApplication(scanBasePackages = {"com.gg.midway.redis.demo"})
public class DemoApplication {

    public static void main(String[] args) {

        ApplicationContext ctx = SpringApplication.run(DemoApplication.class, args);
        System.out.println("Running class full name : " + DemoApplication.class.getCanonicalName());

        String[] beanNames = ctx.getBeanDefinitionNames();
        System.out.println("====================== " );
        System.out.println("bean count = " +  ctx.getBeanDefinitionCount());
        int i = 0;
        for (String str : beanNames) {
            String print_str = "" + (++i) + " : beanName:" + str ;
            System.out.println(print_str);
        }
        System.out.println("====================== " );

        // System.out.println("jedisCacheProvider : " + jedisCacheProvider );
        // jedisCacheProvider.setString("name", "spring boot jedis");
        // System.out.println(jedisCacheProvider.getString("name"));
        // Assert.assertEquals("terrylmay", jedisCacheProvider.getString("name"));

    }


}
