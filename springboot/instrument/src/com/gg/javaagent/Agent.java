package com.gg.javaagent;

import com.gg.instrument.client.Rand;

import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;

public class Agent {

    static private Instrumentation _inst = null;

    public static void premain(String agentArgs, Instrumentation inst) {
        // Param.generatePARAMS(agentArgs);
        System.out.println("Agent.premain() was called.");

        /* Provides services that allow Java programming language agents to instrument programs running on the JVM.*/
        _inst = inst;

        /* ClassFileTransformer : An agent provides an implementation of this interface in order to transform class files.*/
        // ClassFileTransformer trans = new AopAgentTransformer();
        ClassTimer transformer = new ClassTimer();
        /*Registers the supplied transformer.*/
        _inst.addTransformer(transformer);
    }

//    public static void premain(String args, Instrumentation instrumentation) {
//        ClassTimer transformer = new ClassTimer();
//        instrumentation.addTransformer(transformer);
//    }

    public static void agentmain(String args, Instrumentation instrumentation)
            throws UnmodifiableClassException {
        System.out.println("Agent.premain() was called.");
        System.out.println("SUCCESS AGENTMAIN");
        ClassTimer transformer = new ClassTimer();
        instrumentation.addTransformer(transformer, true);
        instrumentation.retransformClasses(Rand.class);
    }
}
