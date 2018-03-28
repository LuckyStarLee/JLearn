package com.luckylee.spi.impl;

import com.luckylee.spi.HelloInterface;

public class ImageHello implements HelloInterface {
    @Override
    public void sayHello() {
        System.out.println("Image Hello");
    }
}
