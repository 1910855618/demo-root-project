package com.demoredis.config;

import com.demoredis.manager.TtlRedisCacheManager;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.math.BigDecimal;
import java.math.BigInteger;

@Configuration
public class RedisConfig extends CachingConfigurerSupport {
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
//        ObjectMapper mapper = new ObjectMapper();
        // 激活默认的类型信息，以便在反序列化时能够正确地恢复对象的类型信息
//        mapper.activateDefaultTyping(LaissezFaireSubTypeValidator.instance, ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY);
        // mapper 中注册 JavaTimeModule 模块，对 java.time 类型的序列化需要该模块
//        mapper.registerModule(new JavaTimeModule());
        // 将 ObjectMapper 设置到 Jackson2JsonRedisSerializer 中
        jackson2JsonRedisSerializer.setObjectMapper(objectMapper());

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

    /**
     *
     * Redis缓存配置：5.配置Redis缓存管理器： RedisCacheManager
     *
     * @param factory redis工厂
     * @return CacheManager
     */
    @Bean
    public CacheManager cacheManager(RedisConnectionFactory factory) {
        RedisSerializer<String> redisSerializer = new StringRedisSerializer();
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        jackson2JsonRedisSerializer.setObjectMapper(objectMapper());

        // 配置序列化（解决乱码的问题）
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(redisSerializer))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(jackson2JsonRedisSerializer))
                .disableCachingNullValues();

        return new TtlRedisCacheManager(RedisCacheWriter.lockingRedisCacheWriter(factory), config);
    }

    /**
     *
     * 全局的对象转化json规则自定义
     *
     * @return {@link ObjectMapper}
     */
    private ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();

        //将 Long,BigInteger,BigDecimal 序列化的时候,转化为 String
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(BigInteger.class, ToStringSerializer.instance);
        simpleModule.addSerializer(BigDecimal.class, ToStringSerializer.instance);
        objectMapper.registerModule(simpleModule);
        // mapper 中注册 JavaTimeModule 模块，对 java.time 类型的序列化需要该模块
        objectMapper.registerModule(new JavaTimeModule());

        // 指定要序列化的域，field,get和set,以及修饰符范围，ANY 是都有包括private和public
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        // 忽略未知字段
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        // 此项必须配置，否则会报java.lang.ClassCastException: java.util.LinkedHashMap cannot be cast to XXX
        // 激活默认的类型信息，以便在反序列化时能够正确地恢复对象的类型信息
        objectMapper.activateDefaultTyping(LaissezFaireSubTypeValidator.instance, ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY);
        // null值、""都不序列化
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        return objectMapper;
    }

    /**
     *
     * 自定义缓存策略，对于同一业务(同一业务逻辑处理的方法，哪怕是集群/分布式系统)，生成的 key 始终一致，对于不同业务则不一致
     */
    @Bean
    public KeyGenerator customKeyGenerator() {
        return (o, method, objects) -> {
            StringBuilder sb = new StringBuilder();
            sb.append(o.getClass().getName());
            sb.append(method.getName());
            for (Object obj : objects) {
                sb.append(obj.toString());
            }
            return sb.toString();
        };
    }
}
