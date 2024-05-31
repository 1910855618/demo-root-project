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
public class WorkQueueConsumer {
    @Resource
    private ObjectMapper objectMapper;

    // 工作模式的消息列队会被这两个消费者负载消费
    @RabbitListener(queues = RabbitMQConstant.WORK_QUEUE)
    public void processOne(byte[] message) throws JsonProcessingException {
        log.info("工作模式 one 收到消息: {}", objectMapper.readValue(new String(message), Map.class));
    }

    @RabbitListener(queues = RabbitMQConstant.WORK_QUEUE)
    public void processTwo(byte[] message) throws JsonProcessingException {
        log.info("工作模式 two 收到消息: {}", objectMapper.readValue(new String(message), Map.class));
    }
}
