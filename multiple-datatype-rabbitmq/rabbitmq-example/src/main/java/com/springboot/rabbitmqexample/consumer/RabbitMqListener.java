package com.springboot.rabbitmqexample.consumer;

import com.springboot.rabbitmqexample.config.RabbitMqConfig;
import com.springboot.rabbitmqexample.dto.Message;
import com.springboot.rabbitmqexample.dto.Order;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = RabbitMqConfig.MESSAGE_ORDER_QUEUE)
public class RabbitMqListener {
    @RabbitHandler
    public void messageListener(Message message) {
        System.out.println("message received from queue = " + message);
    }

    @RabbitHandler
    public void orderListener(Order order) {
        System.out.println("Order received from queue = " + order);
    }

}
