package com.springboot.rabbitmqexample.singleDataTypeQueue.consumer;

import com.springboot.rabbitmqexample.singleDataTypeQueue.config.EmployeeRabbitMqConfig;
import com.springboot.rabbitmqexample.singleDataTypeQueue.dto.Employee;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = EmployeeRabbitMqConfig.EMPLOYEE_QUEUE)
public class EmployeeListener {

    @RabbitHandler
    public void messageListener(Object object) {

        Message message = (Message) object;
        String data = new String(message.getBody());

        System.out.println("employee received from queue = " + data);
        
    }

}
