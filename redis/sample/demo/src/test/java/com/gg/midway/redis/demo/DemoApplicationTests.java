package com.gg.midway.redis.demo;

import com.gg.midway.redis.demo.provider.ICacheProvider;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {DemoApplication.class})
public class DemoApplicationTests {

    @Autowired
    ICacheProvider jedisCacheProvider;

    @Test
    public void contextLoads() {
        System.out.println("jedisCacheProvider : " + jedisCacheProvider );
        jedisCacheProvider.setString("name", "spring boot jedis ");
        System.out.println(jedisCacheProvider.getString("name"));
        Assert.assertEquals("spring boot jedis ", jedisCacheProvider.getString("name"));
    }

}
