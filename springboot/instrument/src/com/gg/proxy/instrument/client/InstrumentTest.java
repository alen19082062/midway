package com.gg.proxy.instrument.client;

public class InstrumentTest {

    static void sleep() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        Rand rand = new Rand();

        for (int i=0;i<10;i++) {
            System.out.println(" ======= " + i + " ========");

            System.out.println(">>> start Rand.run() <<<");
            rand.run();
            System.out.println(">>> end Rand.run() <<<");

            System.out.println(" ");

            System.out.println(">>> start Main.sleep() <<<");
            InstrumentTest.sleep();
            System.out.println(">>> end MAin.sleep() <<<");

            System.out.println();
        }
    }

}
