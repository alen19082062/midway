package com.gg.pico;

import org.picocontainer.Startable;

public class TestClass2 implements Startable {

    @Override
    public void start() {
        System.out.println(TestClass2.class.getSimpleName() + " start");
    }

    @Override
    public void stop() {
        System.out.println(TestClass2.class.getSimpleName() + " stop");
    }

    public void test() {
        System.out.println(TestClass2.class.getSimpleName() + " test");
    }
}
