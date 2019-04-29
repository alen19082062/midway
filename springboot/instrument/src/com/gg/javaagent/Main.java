package com.gg.javaagent;

import com.sun.tools.attach.AgentInitializationException;
import com.sun.tools.attach.AgentLoadException;
import com.sun.tools.attach.AttachNotSupportedException;
import com.sun.tools.attach.VirtualMachine;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, AttachNotSupportedException, AgentLoadException, AgentInitializationException {
        VirtualMachine vm = null;
        try {
            vm = VirtualMachine.attach("29456");
            vm.loadAgent("F:\\git_code\\19082062\\midway\\springboot\\instrument\\src\\agent.jar");
        } finally {
            if (vm != null) {
                vm.detach();
            }
        }
    }
}
