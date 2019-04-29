package com.gg.instrument.client;

/**
 * @author fangfeng
 * @since 2018/8/7
 */
public class Main {

    static void sleep() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        Rand rand = new Rand();

        for (int i=0;i<10;i++) {
            System.out.println(">>> start Rand.run() <<<");
            rand.run();
            System.out.println(">>> end Rand.run() <<<");

            System.out.println();

            System.out.println(">>> start Main.sleep() <<<");
            Main.sleep();
            System.out.println(">>> end Main.sleep() <<<");

            System.out.println();
        }
    }
}
