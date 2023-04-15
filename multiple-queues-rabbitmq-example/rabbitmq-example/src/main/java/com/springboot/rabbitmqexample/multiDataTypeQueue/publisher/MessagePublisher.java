package com.springboot.rabbitmqexample.multiDataTypeQueue.publisher;

import com.springboot.rabbitmqexample.multiDataTypeQueue.config.OrderMessageRabbitMqConfig;
import com.springboot.rabbitmqexample.multiDataTypeQueue.dto.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/message")
public class MessagePublisher {

    @Autowired
    @Qualifier("order-message-rabbit-template")
    RabbitTemplate rabbitTemplate;

    @PostMapping
    public String sendMessage(@RequestBody Message message) {
        rabbitTemplate.convertAndSend(OrderMessageRabbitMqConfig.MESSAGE_ORDER_EXCHANGE, OrderMessageRabbitMqConfig.MESSAGE_ORDER_ROUTING_KEY, message);
        return "Success";
    }

}