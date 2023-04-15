package com.springboot.rabbitmqexample.multiDataTypeQueue.consumer;

import com.springboot.rabbitmqexample.multiDataTypeQueue.config.OrderMessageRabbitMqConfig;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
//@RabbitListener(queues = {OrderMessageRabbitMqConfig.MESSAGE_ORDER_QUEUE, EmployeeRabbitMqConfig.EMPLOYEE_QUEUE})
@RabbitListener(queues = OrderMessageRabbitMqConfig.MESSAGE_ORDER_QUEUE)
public class RabbitQueueListener {

    @RabbitHandler
    public void messageListener(Object object) {

        org.springframework.amqp.core.Message message = (org.springframework.amqp.core.Message) object;
        String data = new String(message.getBody());

        System.out.println("message received from queue = " + data);

    }

//    @RabbitHandler
//    public void messageListener(Message message) {
//        System.out.println("message received from queue = " + message);
//    }
//
//    @RabbitHandler
//    public void orderListener(Order order) {
//        System.out.println("Order received from queue = " + order);
//    }

//    @RabbitHandler
//    public void employeeListener(Employee employee) {
//        System.out.println("employee received from queue = " + employee);
//    }

}
