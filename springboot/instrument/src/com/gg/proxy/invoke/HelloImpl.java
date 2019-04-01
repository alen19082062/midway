package com.gg.proxy.invoke;


public class HelloImpl implements IHello {
    @Override
    public void sayHello() {
        System.out.println("Hello world!");
    }
}
