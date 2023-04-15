package com.springboot.rabbitmqexample.publisher;

import com.springboot.rabbitmqexample.config.RabbitMqConfig;
import com.springboot.rabbitmqexample.dto.Order;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderPublisher {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostMapping
    public String bookOrder(@RequestBody Order order) {
        rabbitTemplate.convertAndSend(RabbitMqConfig.MESSAGE_ORDER_EXCHANGE, RabbitMqConfig.MESSAGE_ORDER_ROUTING_KEY, order);
        return "Success";
    }

}
