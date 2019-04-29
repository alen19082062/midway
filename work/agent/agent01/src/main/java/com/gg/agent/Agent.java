package com.gg.agent;

import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;

public class Agent {
    public static void premain(String agentArgs, Instrumentation instrumentation) {
        System.out.println("Agent.premain(agentArgs,inst) was called.");
        LogTransformer transformer = new LogTransformer();
        instrumentation.addTransformer(transformer);
    }

    public static void agentmain(String args, Instrumentation instrumentation)
            throws UnmodifiableClassException {
        System.out.println("Agent.agentmain() was called.");
        LogTransformer transformer = new LogTransformer();
        instrumentation.addTransformer(transformer, true);
    }
}
