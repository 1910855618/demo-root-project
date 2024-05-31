package com.demorabbitmq.constant;

public interface RabbitMQConstant {
    // 简单模式
    String HELLO_QUEUE = "hello-queue";

    // 工作模式
    String WORK_QUEUE = "work_queue";

    // 订阅发布模式
    String PUBLISH_ONE_QUEUE = "publish_one_queue";
    String PUBLISH_TWO_QUEUE = "publish_two_queue";
    String PUBLISH_THREE_QUEUE = "publish_three_queue";
    String PUBLISH_SUBSCRIBE_EXCHANGE = "publish_subscribe_exchange";

    // 路由模式
    String ROUTER_ONE_QUEUE = "router_one_queue";
    String ROUTER_TWO_QUEUE = "router_two_queue";
    String ROUTER_THREE_QUEUE = "router_three_queue";
    String ROUTER_EXCHANGE = "router_exchange";
    String ROUTER_ONE = "routerOne";
    String ROUTER_TWO = "routerTwo";
    String ROUTER_THREE = "routerThree";

    // 主题模式
    String TOPIC_ONE_QUEUE = "topic_one_queue";
    String TOPIC_TWO_QUEUE = "topic_two_queue";
    String TOPIC_EXCHANGE = "topic_exchange";
    String TOPIC_COM = "com";
    String TOPIC_WWW = "www";
    String TOPIC_LUHANG = "rabbit";
    String PARTITION = ".";
    // 匹配单个词组
    String SINGLE_LIKE = "*";
    // 匹配零或多个词组
    String MANY_LIKE = "#";

    // RPC 模式
    String RPC_QUEUE = "rpc_queue";
}
