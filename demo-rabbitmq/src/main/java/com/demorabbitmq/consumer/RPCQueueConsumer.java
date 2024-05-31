package com.demorabbitmq.consumer;

import com.demorabbitmq.constant.RabbitMQConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RabbitListener(queues = RabbitMQConstant.RPC_QUEUE)
public class RPCQueueConsumer {

    @RabbitHandler
    public String process(byte[] message) {
        return new String(message);
    }
}
