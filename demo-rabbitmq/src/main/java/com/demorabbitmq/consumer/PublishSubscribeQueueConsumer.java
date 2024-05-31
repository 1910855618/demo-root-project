package com.demorabbitmq.consumer;

import com.demorabbitmq.constant.RabbitMQConstant;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

@Slf4j
@Component
public class PublishSubscribeQueueConsumer {
    @Resource
    private ObjectMapper objectMapper;

    // 应为以下列队都绑定了同一个交换机, 在交换机发布消息时会被以下列队同时消费
    @RabbitListener(queues = RabbitMQConstant.PUBLISH_ONE_QUEUE)
    public void processOne(byte[] message) throws JsonProcessingException {
        log.info("订阅发布 one 收到消息: {}", objectMapper.readValue(new String(message), Map.class));
    }

    @RabbitListener(queues = RabbitMQConstant.PUBLISH_TWO_QUEUE)
    public void processTwo(byte[] message) throws JsonProcessingException {
        log.info("订阅发布 two 收到消息: {}", objectMapper.readValue(new String(message), Map.class));
    }

    @RabbitListener(queues = RabbitMQConstant.PUBLISH_THREE_QUEUE)
    public void processThree(byte[] message) throws JsonProcessingException {
        log.info("订阅发布 three 收到消息: {}", objectMapper.readValue(new String(message), Map.class));
    }
}
