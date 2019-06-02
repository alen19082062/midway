package com.gg.midway.lettuce.config;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.io.Serializable;

/**
 * redis默认模板只能存储<String,String>, 也可以用但是每次都要转成json字符串传输
 * 可能很麻烦，可以重写一个模板程序自动加载，可以存<key,object> .
 * 这是一个很重要的加载类，它声明redis存储<String,object>类型
 * 其中@Configuration 代表这个类是一个配置类，
 * @AutoConfigureAfter(RedisAutoConfiguration.class) 这个配置类在内置的配置类
 * 之后在配置，这样就保证我们的配置类生效，并且不会被覆盖配置。

 */
@Configuration
@AutoConfigureAfter(RedisAutoConfiguration.class)
public class RedisCacheAutoConfiguration {

     // 其中需要注意的就是方法名一定要叫 redisTemplate  因为
    // @Bean注解是根据方法名配置这个bean的name的出处。
    @Bean
    public RedisTemplate<String, Serializable> redisCacheTemplate(LettuceConnectionFactory redisConnectionFactory) {
        System.out.println("RedisCacheAutoConfiguration.redisCacheTemplate() ... ");
        RedisTemplate<String, Serializable> template = new RedisTemplate<>();
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        template.setConnectionFactory(redisConnectionFactory);
        return template;
    }
}
