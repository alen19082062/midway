package com.gg.javaagent;

// lib/asm-7.1.jar
import org.objectweb.asm.*;

/**
 * @author fangfeng
 * @since 2018/8/7
 */
public class MyClassWriter extends ClassVisitor {

    private String className;

    public MyClassWriter(int api) {
        super(api);
    }

    public MyClassWriter(int api, ClassVisitor classVisitor) {
        super(api, classVisitor);
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        super.visit(version, access, name, signature, superName, interfaces);
        this.className = name;
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        MethodVisitor mv = super.visitMethod(access, name, descriptor, signature, exceptions);
        if (this.className.contains("com/gg/instrument/client")) {
            mv = new MyMethodWriter(Opcodes.ASM7, mv, className, name, descriptor);
        }
        return mv;
    }
}