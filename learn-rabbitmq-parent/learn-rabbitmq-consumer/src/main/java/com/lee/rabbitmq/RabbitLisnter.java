package com.lee.rabbitmq;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @program: JLearn
 * @author: lucky lee
 * @create: 2018-05-29 10:28
 **/
@Component
public class RabbitLisnter {
    private static  final Logger log=LoggerFactory.getLogger(RabbitLisnter.class);
    private Jackson2JsonMessageConverter jackson2JsonMessageConverter;
    @RabbitListener(queues = {"testQ"})
    public  void recieve(Message message){
       Object o= jackson2JsonMessageConverter.fromMessage(message);
        log.info("recieve msg: "+JSON.toJSONString(o));
    }
    @Autowired
    public void setJackson2JsonMessageConverter(Jackson2JsonMessageConverter jackson2JsonMessageConverter) {
        this.jackson2JsonMessageConverter = jackson2JsonMessageConverter;
    }
}
