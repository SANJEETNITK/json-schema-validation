package com.springboot.rabbitmqexample.publisher;

import com.springboot.rabbitmqexample.config.EmployeeRabbitMqConfig;
import com.springboot.rabbitmqexample.dto.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/message")
public class MessagePublisher {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostMapping()
    public String publishMessage(@RequestBody Message message) {
        rabbitTemplate.convertAndSend(EmployeeRabbitMqConfig.EXCHANGE, EmployeeRabbitMqConfig.MESSAGE_ROUTING_KEY, message);
        return "message published";
    }

}
