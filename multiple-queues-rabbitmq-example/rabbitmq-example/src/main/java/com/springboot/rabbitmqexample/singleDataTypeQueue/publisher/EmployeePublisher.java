package com.springboot.rabbitmqexample.singleDataTypeQueue.publisher;


import com.springboot.rabbitmqexample.singleDataTypeQueue.config.EmployeeRabbitMqConfig;
import com.springboot.rabbitmqexample.singleDataTypeQueue.dto.Employee;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee")
public class EmployeePublisher {

    @Autowired
    @Qualifier("employee-rabbit-template")
    private RabbitTemplate rabbitTemplate;

    @PostMapping()
    public String publishEmployee(@RequestBody Employee employee) {
        rabbitTemplate.convertAndSend(EmployeeRabbitMqConfig.EXCHANGE, EmployeeRabbitMqConfig.EMPLOYEE_ROUTING_QUEUE, employee);
        return "employee published";
    }

}
