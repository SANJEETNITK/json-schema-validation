package com.springboot.rabbitmqexample.consumer;

import com.springboot.rabbitmqexample.config.EmployeeRabbitMqConfig;

import com.springboot.rabbitmqexample.dto.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = EmployeeRabbitMqConfig.MESSAGE_QUEUE)
public class EmployeeListener {

    @RabbitHandler
    public void messageListener(Message message) {
        System.out.println("message received from queue = " + message);
    }

}
