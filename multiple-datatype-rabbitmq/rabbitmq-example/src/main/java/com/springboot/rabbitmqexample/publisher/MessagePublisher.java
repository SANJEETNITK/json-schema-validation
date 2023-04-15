package com.springboot.rabbitmqexample.publisher;

import com.springboot.rabbitmqexample.config.RabbitMqConfig;
import com.springboot.rabbitmqexample.dto.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/message")
public class MessagePublisher {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @PostMapping
    public String sendMessage(@RequestBody Message message) {
        rabbitTemplate.convertAndSend(RabbitMqConfig.MESSAGE_ORDER_EXCHANGE, RabbitMqConfig.MESSAGE_ORDER_ROUTING_KEY, message);
        return "Success";
    }

}