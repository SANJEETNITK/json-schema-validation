package com.springboot.rabbitmqexample.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmployeeRabbitMqConfig {

    public static final String MESSAGE_QUEUE = "message_queue";
    public static final String EXCHANGE = "message_exchange";
    public static final String MESSAGE_ROUTING_KEY = "message_routing_key";

    @Bean
    public RabbitTemplate employeeRabbitTemplate (final ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }

    @Bean
    public Queue queue() {
        return new Queue(MESSAGE_QUEUE);
    }

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(EXCHANGE);
    }


    @Bean
    public Binding binding() {
        return BindingBuilder.bind(queue()).to(topicExchange()).with(MESSAGE_ROUTING_KEY);
    }

    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

}

