package com.gg.javaagent;

import java.util.HashMap;
import java.util.Map;

/**
 * @author fangfeng
 * @since 2018/8/7
 */
public class StaticTimer {

    private static Map<String, Long> timer = new HashMap<>();

    public static void timing(String identifier) {
        if (timer.containsKey(identifier)) {
            Long start = timer.get(identifier);
            timer.remove(identifier);
            System.out.println(String.format("Execute %s : %f ms", identifier, (System.nanoTime() - start) / 1000000.0));
        } else {
            timer.put(identifier, System.nanoTime());
        }
    }
}
