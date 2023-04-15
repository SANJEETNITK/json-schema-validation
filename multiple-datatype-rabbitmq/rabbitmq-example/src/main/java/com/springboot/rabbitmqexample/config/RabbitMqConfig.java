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
public class RabbitMqConfig {

    public static final String MESSAGE_ORDER_QUEUE = "message_order_queue";
    public static final String MESSAGE_ORDER_EXCHANGE = "message_order_exchange";
    public static final String MESSAGE_ORDER_ROUTING_KEY = "order_routing_queue";

    @Bean
    public RabbitTemplate rabbitTemplate (final ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(orderMessageConverter());
        return rabbitTemplate;
    }

    @Bean
    public Queue orderMessageQueue() {
        return new Queue(MESSAGE_ORDER_QUEUE);
    }

    @Bean
    public TopicExchange orderMessageTopicExchange() {
        return new TopicExchange(MESSAGE_ORDER_EXCHANGE);
    }

    @Bean
    public Binding orderBinding() {
        return BindingBuilder.bind(orderMessageQueue()).to(orderMessageTopicExchange()).with(MESSAGE_ORDER_ROUTING_KEY);
    }

    @Bean
    public Binding messageBinding() {
        return BindingBuilder.bind(orderMessageQueue()).to(orderMessageTopicExchange()).with(MESSAGE_ORDER_ROUTING_KEY);
    }

    @Bean
    public MessageConverter orderMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

}
