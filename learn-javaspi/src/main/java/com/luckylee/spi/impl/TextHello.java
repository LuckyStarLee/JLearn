package com.luckylee.spi.impl;

import com.luckylee.spi.HelloInterface;

public class TextHello implements HelloInterface {
    @Override
    public void sayHello() {
        System.out.println("Text Hello");
    }
}
