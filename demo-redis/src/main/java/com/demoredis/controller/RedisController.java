package com.demoredis.controller;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Optional;

@RestController
@RequestMapping("/redis")
public class RedisController {
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    // String 存
    @GetMapping("/set")
    public String set(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
        return "success";
    }
    // String 取
    @GetMapping("/get")
    public String get(String key) {
        return Optional.ofNullable(redisTemplate.opsForValue().get(key)).orElse("empty").toString();
    }
}
