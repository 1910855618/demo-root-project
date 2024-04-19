package com.demoredis.template;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

@Component
public class JsonRedisTemplate extends RedisTemplate<String, Object> {
    public JsonRedisTemplate(RedisConnectionFactory factory) {
        // 构造函数注入 RedisConnectionFactory，设置到父类
        super.setConnectionFactory(factory);

        // 使用 Jackson 提供的通用 Serializer
        ObjectMapper mapper = new ObjectMapper();
        // 激活默认的类型信息，以便在反序列化时能够正确地恢复对象的类型信息
        mapper.activateDefaultTyping(LaissezFaireSubTypeValidator.instance, ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY);
        // mapper 中注册 JavaTimeModule 模块，对 java.time 类型的序列化需要该模块
        mapper.registerModule(new JavaTimeModule());

        // 使用 Jackson 提供的通用 Serializer
        GenericJackson2JsonRedisSerializer serializer = new GenericJackson2JsonRedisSerializer(mapper);

        // String 类型的 key/value 序列化
        super.setKeySerializer(StringRedisSerializer.UTF_8);
        super.setValueSerializer(serializer);

        // Hash 类型的 key/value 序列化
        super.setHashKeySerializer(StringRedisSerializer.UTF_8);
        super.setHashValueSerializer(serializer);
    }
}
