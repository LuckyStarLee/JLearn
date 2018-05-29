package com.lee.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @program: JLearn
 * @author: lucky lee
 * @create: 2018-05-29 10:28
 **/
@Component
public class RabbitLisnter {
    private static  final Logger log=LoggerFactory.getLogger(RabbitLisnter.class);
    @RabbitListener(queues = {"testQ"})
    public  void recieve(Object message){
        log.info("recive msg: "+message);
    }
}
