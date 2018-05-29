package com.lee.rabbitmq;

/**
 * @program: JLearn
 * @author: lucky lee
 * @create: 2018-05-29 14:16
 **/
public class MyMessage {
    private String id;
    private String context;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }
}
