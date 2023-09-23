package com.itstep.hello_spring.controllers;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/redis")
public class RedisController {
    final
    RedisTemplate<String, String> redisTemplate;

    public RedisController(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @GetMapping("")
    public String testRedis() {
        redisTemplate.opsForValue().set("SomeKey", "SomeVal");

        String res = redisTemplate.opsForValue().get("SomeKey");
        return res;
    }
}
