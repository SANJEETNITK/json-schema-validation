package com.springboot.rabbitmqexample.multiDataTypeQueue.config;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderMessageRabbitMqConfig {

    public static final String MESSAGE_ORDER_QUEUE = "message_order_queue";
    public static final String MESSAGE_ORDER_EXCHANGE = "message_order_exchange";
    public static final String MESSAGE_ORDER_ROUTING_KEY = "order_routing_queue";

    @Bean
    @Qualifier("order-message-rabbit-template")
    public RabbitTemplate rabbitTemplate (final ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(orderMessageConverter());
        return rabbitTemplate;
    }

    @Bean
    @Qualifier("order-message-queue")
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

//    @Bean
//    public List<Binding> multipleBinding() {
//        Binding orderQueueBinding = BindingBuilder.bind(orderMessageQueue()).to(orderMessageTopicExchange()).with(ORDER_ROUTING_KEY);
//        Binding messageQueueBinding = BindingBuilder.bind(orderMessageQueue()).to(orderMessageTopicExchange()).with(MESSAGE_ROUTING_KEY);
//        List<Binding> bindings = new ArrayList<>();
//        bindings.add(orderQueueBinding);
//        bindings.add(messageQueueBinding);
//        return bindings;
//    }

    @Bean
    public MessageConverter orderMessageConverter() {

        return new Jackson2JsonMessageConverter();


//        Jackson2JsonMessageConverter converter = new Jackson2JsonMessageConverter();
//        DefaultClassMapper classMapper = new DefaultClassMapper();
//
//        Map<String, Class<?>> idClassMapping = new HashMap<>();
//        idClassMapping.put("com.springboot.rabbitmqexample.multiDataTypeQueue.dto.Message", Message.class);
//        idClassMapping.put("com.springboot.rabbitmqexample.multiDataTypeQueue.dto.Order", Order.class);
//        classMapper.setIdClassMapping(idClassMapping);
//        converter.setClassMapper(classMapper);
//        return converter;
    }

}
