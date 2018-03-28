package com.luckylee.spi;

import java.util.ServiceLoader;


public class SPIMain {
    public static void main(String[] args) {
        ServiceLoader<HelloInterface> loaders= ServiceLoader.load(HelloInterface.class);
        for (HelloInterface itf :loaders ) {
            itf.sayHello();
        }
        
    }
}
