package com.springboot.rabbitmqexample.multiDataTypeQueue.publisher;

import com.springboot.rabbitmqexample.multiDataTypeQueue.config.OrderMessageRabbitMqConfig;
import com.springboot.rabbitmqexample.multiDataTypeQueue.dto.Order;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderPublisher {

    @Autowired
//    @Qualifier("order-message-rabbit-template")
    private RabbitTemplate rabbitTemplate;

    @PostMapping("/{restaurantName}")
    public String bookOrder(@RequestBody Order order, @PathVariable("restaurantName") String restaurantName) {
        rabbitTemplate.convertAndSend(OrderMessageRabbitMqConfig.MESSAGE_ORDER_EXCHANGE, OrderMessageRabbitMqConfig.MESSAGE_ORDER_ROUTING_KEY, order);
        return "Success";
    }

}
