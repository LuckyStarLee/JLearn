package com.lee.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @program: JLearn
 * @author: lucky lee
 * @create: 2018-05-28 18:45
 **/
@Component
public class Schedule {
    private static  final Logger log=LoggerFactory.getLogger(Schedule.class);
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Value("${messaage.test.exchange}")
    private String exchange;
    @Value("${messaage.test.routekey}")
    private String routeKey;
    @Scheduled(fixedRate = 5000)
    public void print(){
        //log.info("test schedule...");
        String context="exchange: "+exchange+" routeKey: "+routeKey+" ...";
        CorrelationData data=new CorrelationData(UUID.randomUUID().toString());
        rabbitTemplate.convertAndSend(exchange,routeKey,context,data);
    }
}
