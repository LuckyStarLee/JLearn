package com.lee.rabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: JLearn
 * @author: lucky lee
 * @create: 2018-05-29 10:20
 **/
@Configuration
public class RabbitMqConfig {
    @Value("${messaage.test.exchange}")
    private String exchange;
    @Value("${messaage.test.routekey}")
    private String routeKey;

    @Bean
    public Queue queue() {
        return QueueBuilder.nonDurable("testQ").build();
    }

    @Bean
    public Exchange exchange() {
        return ExchangeBuilder.topicExchange(exchange).build();
    }
    @Bean
   public Binding bind(){
       return BindingBuilder.bind(queue()).to(exchange()).with(routeKey).noargs();
    }
    @Bean
    public static Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
