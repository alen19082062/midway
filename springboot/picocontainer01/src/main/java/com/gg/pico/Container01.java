package com.gg.pico;

import org.picocontainer.MutablePicoContainer;
import org.picocontainer.PicoBuilder;
import org.picocontainer.Startable;

public class Container01 {
    public static void main(String[] args) {
        MutablePicoContainer container =
                new PicoBuilder().withLocking().withCaching().withLifecycle().build();
        container.addComponent("k1", TestClass1.class);
        container.addComponent("k2", TestClass2.class);
        container.start();
        // 取出
        TestClass1 c1 = (TestClass1) container.getComponent("k1");
        System.out.println("main()  get TestClass1 from container , c1 = " + c1 );
        c1.test();
        TestClass2 c2 = (TestClass2) container.getComponent("k2");
        System.out.println("main()  get TestClass1 from container , c2 = " + c2 );
        c2.test();
        container.stop();
    }

}
