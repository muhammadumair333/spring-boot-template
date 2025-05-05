package com.mission.spring.journalApp.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
public class RedisTest {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void sendEmail(){
        redisTemplate.opsForValue().set("email", "a@gmail.come");
        Assertions.assertEquals("a@gmail.come",
                redisTemplate.opsForValue().get("email"));

    }

    @Test
    public void getSalary(){

        Assertions.assertEquals("10k",
                redisTemplate.opsForValue().get("salary"));

    }
}
