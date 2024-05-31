package com.demorabbitmq.config;

import com.demorabbitmq.constant.RabbitMQConstant;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    /**
     * 简单模式
     * */
    // 列队
    @Bean
    public Queue helloQueue() {
        return new Queue(RabbitMQConstant.HELLO_QUEUE);
    }

    /**
     * 工作模式
     * */
    // 列队
    @Bean
    public Queue workQueue() {
        return new Queue(RabbitMQConstant.WORK_QUEUE);
    }

    /**
     * 订阅发布模式
     * */
    // 列队
    @Bean
    public Queue publishOneQueue() {
        return new Queue(RabbitMQConstant.PUBLISH_ONE_QUEUE);
    }

    @Bean
    public Queue publishTwoQueue() {
        return new Queue(RabbitMQConstant.PUBLISH_TWO_QUEUE);
    }

    @Bean
    public Queue publishThreeQueue() {
        return new Queue(RabbitMQConstant.PUBLISH_THREE_QUEUE);
    }

    // 交换机
    // 扇形交换机没有路由键的概念，只需将队列绑定在交换机上，发送到交换机上的消息会转发到交换机所以绑定的队列里面，类似广播，只要打开收音机都能接收到广播消息。
    // 扇形交换机应用于发布订阅模式。
    @Bean
    public FanoutExchange publishExchange() {
        return new FanoutExchange(RabbitMQConstant.PUBLISH_SUBSCRIBE_EXCHANGE, true, false);
    }

    // 列队绑定交换机
    @Bean
    public Binding bindingOneDirect() {
        return BindingBuilder.bind(publishOneQueue()).to(publishExchange());
    }

    @Bean
    public Binding bindingTwoDirect() {
        return BindingBuilder.bind(publishTwoQueue()).to(publishExchange());
    }

    @Bean
    public Binding bindingThreeDirect() {
        return BindingBuilder.bind(publishThreeQueue()).to(publishExchange());
    }

    /**
     * 路由模式
     * */
    // 列队
    @Bean
    public Queue routerOneQueue() {
        return new Queue(RabbitMQConstant.ROUTER_ONE_QUEUE);
    }

    @Bean
    public Queue routerTwoQueue() {
        return new Queue(RabbitMQConstant.ROUTER_TWO_QUEUE);
    }

    @Bean
    public Queue routerThreeQueue() {
        return new Queue(RabbitMQConstant.ROUTER_THREE_QUEUE);
    }

    // 交换机
    // 直连交换机被应用在路由模式下，该交换机需要通过特定的routingKey来绑定队列，交换机只有接收到了匹配的routingKey才会将消息转发到对应的队列中，否则就不会转发消息。
    @Bean
    public DirectExchange routerExchange() {
        return new DirectExchange(RabbitMQConstant.ROUTER_EXCHANGE, true, false);
    }

    // 列队绑定交换机跟踪路由
    @Bean
    public Binding bindingRouterOneDirect() {
        return BindingBuilder.bind(routerOneQueue()).to(routerExchange()).with(RabbitMQConstant.ROUTER_ONE);
    }

    @Bean
    public Binding bindingRouterTwoDirect() {
        return BindingBuilder.bind(routerTwoQueue()).to(routerExchange()).with(RabbitMQConstant.ROUTER_TWO);
    }

    @Bean
    public Binding bindingRouterThreeDirect() {
        return BindingBuilder.bind(routerThreeQueue()).to(routerExchange()).with(RabbitMQConstant.ROUTER_THREE);
    }

    /**
     * 主题模式
     * */
    // 列队
    @Bean
    public Queue topicOneQueue() {
        return new Queue(RabbitMQConstant.TOPIC_ONE_QUEUE);
    }

    @Bean
    public Queue topicTwoQueue() {
        return new Queue(RabbitMQConstant.TOPIC_TWO_QUEUE);
    }

    // 交换机
    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(RabbitMQConstant.TOPIC_EXCHANGE, true, false);
    }

    // 列队绑定主题交换机
    // .com 之前必须有且只有一个词组才能被匹配
    @Bean
    public Binding bindingTopicOneDirect() {
        return BindingBuilder.bind(topicOneQueue()).to(topicExchange()).with(
                String.join("",
                        RabbitMQConstant.SINGLE_LIKE,
                        RabbitMQConstant.PARTITION,
                        RabbitMQConstant.TOPIC_COM)
        );
    }

    // www. 之后的任意词组都会被匹配
    @Bean
    public Binding bindingTopicTwoDirect() {
        return BindingBuilder.bind(topicTwoQueue()).to(topicExchange()).with(
                String.join("",
                        RabbitMQConstant.TOPIC_WWW,
                        RabbitMQConstant.PARTITION,
                        RabbitMQConstant.MANY_LIKE)
        );
    }

    /**
     * RPC 模式
     * */
    // 列队
    @Bean
    public Queue rpcQueue() {
        return new Queue(RabbitMQConstant.RPC_QUEUE);
    }
}
