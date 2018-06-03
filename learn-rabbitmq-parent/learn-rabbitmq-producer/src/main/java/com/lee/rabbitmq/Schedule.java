package com.lee.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.DependsOn;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @program: JLearn
 * @author: lucky lee
 * @create: 2018-05-28 18:45
 **/
@Component
@DependsOn({"rabbitmqConfig"})
public class Schedule {
    private static final Logger log = LoggerFactory.getLogger(Schedule.class);
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${message.test.exchange}")
    private String exchange;
    @Value("${message.test.routekey}")
    private String routeKey;

    @Scheduled(fixedRate = 5000)
    public void print() {
        //log.info("test schedule...");
        String context = "exchange: " + exchange + " routeKey: " + routeKey + " ...";
        MyMessage myMessage = new MyMessage();
        String id = UUID.randomUUID().toString();
        myMessage.setId(id);
        myMessage.setContext(context);
        CorrelationData data = new CorrelationData(id);
        MessageProperties properties = new MessageProperties();
        Message message = rabbitTemplate.getMessageConverter().toMessage(myMessage, properties);
        rabbitTemplate.convertAndSend(exchange, routeKey, message, data);
    }

}
