package com.gg.proxy.instrument.javaagent;

import java.lang.instrument.Instrumentation;

public class Agent {
    // premain 将 JVM 初始化后，main(String... ) 执行前调用
    public static void premain(String args, Instrumentation instrumentation) {
        // new 一个转换器实例
        ClassTimer transformer = new ClassTimer();
        instrumentation.addTransformer(transformer);
    }

    // 之后的 agentmain(...) 将在这里提供，暂时隐去，避免对对读者产生干扰

}
