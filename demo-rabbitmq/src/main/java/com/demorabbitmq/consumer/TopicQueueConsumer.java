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
public class TopicQueueConsumer {
    @Resource
    private ObjectMapper objectMapper;

    // 应为以下列队都绑定了同一个交换机, 在交换机发布消息时会被以下列队同时消费, 但需要按路由分发
    @RabbitListener(queues = RabbitMQConstant.TOPIC_ONE_QUEUE)
    public void processOne(byte[] message) throws JsonProcessingException {
        log.info("主题 one 收到消息(*.com): {}", objectMapper.readValue(new String(message), Map.class));
    }

    @RabbitListener(queues = RabbitMQConstant.TOPIC_TWO_QUEUE)
    public void processTwo(byte[] message) throws JsonProcessingException {
        log.info("主题 two 收到消息(www.#): {}", objectMapper.readValue(new String(message), Map.class));
    }
}
