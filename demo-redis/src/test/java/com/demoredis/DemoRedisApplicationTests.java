package com.demoredis;

import com.demoredis.template.JsonRedisTemplate;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Range;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.data.redis.connection.stream.MapRecord;
import org.springframework.data.redis.connection.stream.RecordId;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StreamOperations;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@Log4j2
@SpringBootTest
class DemoRedisApplicationTests {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private JsonRedisTemplate jsonRedisTemplate;
    // 配置 RedisTemplate Bean 后可以直接注入 RedisTemplate
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    void contextLoads() {
        // 设置
        stringRedisTemplate.opsForValue().set("title", "颃の主页", Duration.ofMinutes(5));
        // 读取
        String val = stringRedisTemplate.opsForValue().get("title");
        log.info("value={}", val);

        // Map
        Map<String, Object> map = new HashMap<>();
        map.put("title", "颃の主页");
        map.put("url", "https://luhang.fun");
        map.put("createAt", LocalDateTime.now());

        // 设置 key/value
        jsonRedisTemplate.opsForValue().set("key1-string", map, Duration.ofMinutes(5));
        // 读取 key/value
        map = (Map<String, Object>) this.jsonRedisTemplate.opsForValue().get("key1-string");
        log.info("map={}", map);
        jsonRedisTemplate.opsForHash().put("key2-hash", "app", map);
        map = (Map<String, Object>) jsonRedisTemplate.opsForHash().get("key2-hash", "app");
        log.info("map={}", map);

        // Map
        Map<String, Object> map2 = new HashMap<>();
        map2.put("title", "颃の主页");
        map2.put("url", "https://luhang.fun");
        map2.put("createAt", LocalDateTime.now());
        redisTemplate.opsForValue().set("key2-string", map2, Duration.ofMinutes(5));
        map2 = (Map<String, Object>) redisTemplate.opsForValue().get("key2-string");
        log.info("map={}", map2);
    }

    @Test
    void redisTest() {
        // 存储String类型的值
        redisTemplate.opsForValue().set("stringKey", "stringValue");
        // 读取String类型的值
        String stringKey = (String) redisTemplate.opsForValue().get("stringKey");
        log.info("stringKey = {}", stringKey);

        // 存储Hash类型的值
        Map<String, String> hashValue = new HashMap<>();
        hashValue.put("field1", "value1");
        hashValue.put("field2", "value2");
        redisTemplate.opsForHash().putAll("hashKey", hashValue);
        // 读取Hash类型的值
        Map<Object, Object> hashKey = redisTemplate.opsForHash().entries("hashKey");
        log.info("hashKey = {}", hashKey);

        // 存储List类型的值
        Arrays.asList("value1", "value2", "value3").forEach(item -> redisTemplate.opsForList().leftPush("listKey", item));
        // 读取List类型的值
        List<Object> listKey = redisTemplate.opsForList().range("listKey", 0, -1);
        log.info("listKey = {}", listKey);

        // 存储Set类型的值
        Set<String> set = new HashSet<>(Arrays.asList("value1", "value2", "value3"));
        set.forEach(item -> redisTemplate.opsForSet().add("setKey", item));
        // 读取Set类型的值
        Set<Object> setKey = redisTemplate.opsForSet().members("setKey");
        log.info("setKey = {}", setKey);

        // 存储ZSet类型的值（有排序的 set 集合）
        AtomicInteger counter = new AtomicInteger(0);
        Arrays.asList("value1", "value2", "value3").forEach(item -> redisTemplate.opsForZSet().add("zSetKey", item, counter.incrementAndGet()));
        // 读取ZSet类型的值
        Set<Object> zSetKey = redisTemplate.opsForZSet().range("zSetKey", 0, -1);
        log.info("zSetKey = {}", zSetKey);

        // 设置键的过期时间（秒）
        redisTemplate.expire("stringKey", 60, TimeUnit.SECONDS);
        long remainingTime = redisTemplate.getExpire("stringKey", TimeUnit.SECONDS);
        log.info("stringKey remainingTime: {}s", remainingTime);
        // 移除键的过期时间，使其永久有效
        redisTemplate.persist("stringKey");
        remainingTime = redisTemplate.getExpire("stringKey", TimeUnit.SECONDS);
        log.info("stringKey remainingTime: {}s", remainingTime);
    }

}
