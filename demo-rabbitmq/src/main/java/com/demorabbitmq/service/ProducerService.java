package com.demorabbitmq.service;

import com.demorabbitmq.constant.RabbitMQConstant;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

@Service
public class ProducerService {
    @Resource
    private AmqpTemplate amqpTemplate;
    @Resource
    private ObjectMapper objectMapper;

    // 生产一条简单消息
    public void send(String message) {
        amqpTemplate.convertAndSend(RabbitMQConstant.HELLO_QUEUE, message);
    }

    // 生产一条消息提供给工作模式列队
    public void sendWork(Map<String, Object> message) throws JsonProcessingException {
        amqpTemplate.convertAndSend(
                RabbitMQConstant.WORK_QUEUE,
                new Message(objectMapper.writeValueAsBytes(message), new MessageProperties())
        );
    }

    // 生产一条消息通过交换机发布给绑定了该交换机的列队
    public void sendSubscribe(Map<String, Object> message) throws JsonProcessingException {
        amqpTemplate.convertAndSend(
                RabbitMQConstant.PUBLISH_SUBSCRIBE_EXCHANGE,
                RabbitMQConstant.SINGLE_LIKE,
                new Message(objectMapper.writeValueAsBytes(message), new MessageProperties())
        );
    }

    // 生产消息通过交换机发布给绑定了该交换机的列队, 按路由分发消息
    public void sendRouter(Map<String, Object> message) throws JsonProcessingException {
        message.put("router", "one");
        amqpTemplate.convertAndSend(
                RabbitMQConstant.ROUTER_EXCHANGE,
                RabbitMQConstant.ROUTER_ONE,
                new Message(objectMapper.writeValueAsBytes(message), new MessageProperties())
        );
        message.put("router", "two");
        amqpTemplate.convertAndSend(
                RabbitMQConstant.ROUTER_EXCHANGE,
                RabbitMQConstant.ROUTER_TWO,
                new Message(objectMapper.writeValueAsBytes(message), new MessageProperties())
        );
        message.put("router", "three");
        amqpTemplate.convertAndSend(
                RabbitMQConstant.ROUTER_EXCHANGE,
                RabbitMQConstant.ROUTER_THREE,
                new Message(objectMapper.writeValueAsBytes(message), new MessageProperties())
        );
    }

    // 生产消息通过交换机发布给绑定了该交换机的列队, 按主题匹配
    public void sendTopic(Map<String, Object> message) throws JsonProcessingException {
        String val = String.join("",
                RabbitMQConstant.TOPIC_LUHANG,
                RabbitMQConstant.PARTITION,
                RabbitMQConstant.TOPIC_COM);
        message.put("topic", val);
        amqpTemplate.convertAndSend(RabbitMQConstant.TOPIC_EXCHANGE, val,
                new Message(objectMapper.writeValueAsBytes(message), new MessageProperties()));
        val = String.join("",
                RabbitMQConstant.TOPIC_WWW,
                RabbitMQConstant.PARTITION,
                RabbitMQConstant.TOPIC_LUHANG,
                RabbitMQConstant.PARTITION,
                RabbitMQConstant.TOPIC_COM);
        message.put("topic", val);
        amqpTemplate.convertAndSend(RabbitMQConstant.TOPIC_EXCHANGE, val,
                new Message(objectMapper.writeValueAsBytes(message), new MessageProperties()));
        val = String.join("",
                RabbitMQConstant.TOPIC_WWW,
                RabbitMQConstant.PARTITION,
                RabbitMQConstant.TOPIC_LUHANG);
        message.put("topic", val);
        amqpTemplate.convertAndSend(RabbitMQConstant.TOPIC_EXCHANGE, val,
                new Message(objectMapper.writeValueAsBytes(message), new MessageProperties()));
    }

    // 生产一条消息并获得列队消费者的返回
    public Object sendRPC(Map<String, Object> message) throws JsonProcessingException {
        return amqpTemplate.convertSendAndReceive(RabbitMQConstant.RPC_QUEUE, new Message(objectMapper.writeValueAsBytes(message), new MessageProperties()));
    }
}
