package com.gg.midway.asm.demo;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import static org.objectweb.asm.Opcodes.ASM7;

 class AddSecurityCheckMethodAdapter extends MethodVisitor  implements Opcodes{
    public AddSecurityCheckMethodAdapter(MethodVisitor mv) {
        super(Opcodes.ASM7 ,mv);
    }


    public void visitCode() {
        System.out.println("AddSecurityCheckMethodAdapter.visitCode() add check ...");
        visitMethodInsn(Opcodes.INVOKESTATIC, "com/gg/midway/asm/demo/SecurityChecker",
                "check", "()V");
    }

 }

