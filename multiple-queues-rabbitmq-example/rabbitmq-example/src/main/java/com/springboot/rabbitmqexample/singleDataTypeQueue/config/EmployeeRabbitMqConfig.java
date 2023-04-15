package com.springboot.rabbitmqexample.singleDataTypeQueue.config;


import com.springboot.rabbitmqexample.singleDataTypeQueue.dto.Employee;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.DefaultClassMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class EmployeeRabbitMqConfig {

    public static final String EMPLOYEE_QUEUE = "employee_queue";
    public static final String EXCHANGE = "employee_exchange";
    public static final String EMPLOYEE_ROUTING_QUEUE = "employee_routing_queue";

    @Bean
    @Qualifier("employee-rabbit-template")
    public RabbitTemplate employeeRabbitTemplate (final ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(employeeConverter());
        return rabbitTemplate;
    }

    @Bean
    @Qualifier("employee-queue")
    public Queue employeeQueue() {
        return new Queue(EMPLOYEE_QUEUE);
    }

    @Bean
    public TopicExchange employeeTopicExchange() {
        return new TopicExchange(EXCHANGE);
    }


    @Bean
    @Qualifier("employee-binding")
    public Binding employeeBinding() {
        return BindingBuilder.bind(employeeQueue()).to(employeeTopicExchange()).with(EMPLOYEE_ROUTING_QUEUE);
    }

    @Bean
    public MessageConverter employeeConverter() {
//        return new Jackson2JsonMessageConverter();

        Jackson2JsonMessageConverter converter = new Jackson2JsonMessageConverter();
        DefaultClassMapper classMapper = new DefaultClassMapper();

        Map<String, Class<?>> idClassMapping = new HashMap<>();
        idClassMapping.put("com.springboot.rabbitmqexample.singleDataTypeQueue.dto.Employee", Employee.class);
        classMapper.setIdClassMapping(idClassMapping);
        converter.setClassMapper(classMapper);
        return converter;
    }

}

