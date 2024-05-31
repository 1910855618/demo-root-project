package com.demorabbitmq.consumer;

import com.demorabbitmq.constant.RabbitMQConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RabbitListener(queues = RabbitMQConstant.HELLO_QUEUE)
public class HelloQueueConsumer {
    // 消费者处理消息
    @RabbitHandler
    public void process(String message) {
        log.info("简单模式收到消息: {}", message);
    }
}
