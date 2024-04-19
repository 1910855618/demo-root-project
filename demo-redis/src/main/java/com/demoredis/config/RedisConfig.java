package com.demoredis.config;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {
    // 声明一个名为 redisTemplate 的 Bean 方法，用于创建并配置一个 RedisTemplate 实例
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
        // 创建一个 RedisTemplate 实例
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        // 将 RedisTemplate 与 Redis 连接工厂关联起来
        redisTemplate.setConnectionFactory(factory);

        // 创建 Jackson2JsonRedisSerializer 对象，用于将对象序列化为 JSON 格式
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        // 创建 ObjectMapper 对象，用于配置 JSON 序列化器
        ObjectMapper mapper = new ObjectMapper();
        // 激活默认的类型信息，以便在反序列化时能够正确地恢复对象的类型信息
        mapper.activateDefaultTyping(LaissezFaireSubTypeValidator.instance, ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY);
        // mapper 中注册 JavaTimeModule 模块，对 java.time 类型的序列化需要该模块
        mapper.registerModule(new JavaTimeModule());
        // 将 ObjectMapper 设置到 Jackson2JsonRedisSerializer 中
        jackson2JsonRedisSerializer.setObjectMapper(mapper);

        // 创建 StringRedisSerializer 对象，用于将键序列化为字符串
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        // 配置 RedisTemplate 的 key 和 hash key 的序列化器为 StringRedisSerializer
        redisTemplate.setKeySerializer(stringRedisSerializer);
        redisTemplate.setHashKeySerializer(stringRedisSerializer);
        // 配置 RedisTemplate 的 value 和 hash value 的序列化器为 Jackson2JsonRedisSerializer
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);

        // 确保配置的完整性
        redisTemplate.afterPropertiesSet();

        // 返回配置好的 RedisTemplate 实例
        return redisTemplate;
    }
}
