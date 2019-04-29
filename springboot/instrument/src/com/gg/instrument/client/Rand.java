package com.gg.instrument.client;

public class Rand {
    public void run() {
        System.out.println(String.format("Rand.run() begin ..."));

        while (true) {
            double rand = Math.random();
            if (rand > 0.995) {
                System.out.println(String.format("get random, values %f", rand));
                return;
            }
        }
    }
}
