package com.example.UserService.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String EXCHANGE_NAME = "user.exchange";
    public static final String METRO_CARD_BUY_ROUTING_KEY = "user.card.buy";

    @Bean
    public TopicExchange userExchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }

    @Bean
    public Queue metroCardQueue() {
        return new Queue("metro.card.buy.queue");
    }

    @Bean
    public Binding bindingMetroCard(Queue metroCardQueue, TopicExchange userExchange) {
        return BindingBuilder.bind(metroCardQueue).to(userExchange).with(METRO_CARD_BUY_ROUTING_KEY);
    }
}
