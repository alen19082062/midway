package com.gg.midway.redis.demo.web;

import com.gg.midway.redis.demo.config.StandaloneRedisConfig;
import com.gg.midway.redis.demo.provider.ICacheProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Controller
 * 消费者控制层
 */
@RestController
public class Controller {

    @Autowired(required = false)
    StandaloneRedisConfig standaloneRedisConfig;
    //@Autowired(required = false)
    //ClusterRedisConfig clusterRedisConfig;

    @Autowired
    RedisConnectionFactory redisConnectionFactory;
    // 注入配置文件上下文
    @Autowired
    private ConfigurableApplicationContext applicationContext;

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        JedisConnectionFactory factory = null;
        System.out.println("redisConnectionFactory() standaloneRedisConfig : " +  standaloneRedisConfig);
        System.out.println("redisConnectionFactory() host : " +  standaloneRedisConfig.getHost());
        System.out.println("redisConnectionFactory() port : " +  standaloneRedisConfig.getPort());

        if (standaloneRedisConfig != null) {
            factory = new JedisConnectionFactory(new RedisStandaloneConfiguration(standaloneRedisConfig.getHost(), standaloneRedisConfig.getPort()));
            // factory.setPassword("2000@cylife");
            System.out.println("redisConnectionFactory() factory : " +  factory );
            return factory;
        }

//        if (clusterRedisConfig != null) {
//            JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
//            RedisClusterConfiguration redisClusterConfiguration = new RedisClusterConfiguration(Arrays.asList(clusterRedisConfig.getNodes().split(",")));
//            redisClusterConfiguration.setMaxRedirects(clusterRedisConfig.getMaxRedirects());
//            redisClusterConfiguration.setPassword(clusterRedisConfig.getPassword());
//            factory = new JedisConnectionFactory(redisClusterConfiguration, jedisPoolConfig);
//        }

        return factory;
    }

    @Bean
    public StringRedisTemplate stringRedisTemplate() {
        return new StringRedisTemplate(redisConnectionFactory);
    }

    // @Autowired
    // static ICacheProvider jedisCacheProvider;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @RequestMapping("/hi")
    public Map<String, String> hi() {

        stringRedisTemplate.opsForValue().set("name", "spring boot");
        System.out.println("stringRedisTemplate : " + stringRedisTemplate );

        // System.out.println("jedisCacheProvider : " + jedisCacheProvider );
        // jedisCacheProvider.setString("name", "spring boot jedis");
        // String value = jedisCacheProvider.getString("name");
        String value  = stringRedisTemplate.opsForValue().get("name") ;
        System.out.println(value);

        System.out.println("Running class full name : " + this.getClass().getCanonicalName());
        System.out.println("hi() name : " + value );
        Map<String,String> map = new HashMap<>() ;
        map.put("name",value);
        return map ;
    }

    @RequestMapping("/conf")
    public Map<String, Object>  conf() {
        System.out.println("Running class full name : " + this.getClass().getCanonicalName());
        // String value = applicationContext.getEnvironment().getProperty("user.name");
        // String value = applicationContext.getEnvironment().getgetProperty("user.name");
        Map<String,Object> map = applicationContext.getEnvironment().getSystemProperties();
        String str = map.toString();
        System.out.println("conf() properities : " + str );
        return map ;
    }

}
