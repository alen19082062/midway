package com.gg.midway.asm.aop.demo;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class AopMethod extends MethodVisitor implements Opcodes {
    public AopMethod(int api, MethodVisitor mv){
        super(ASM7 ,mv);
    }

    public void visitCode(){
        super.visitCode();
        this.visitMethodInsn(INVOKESTATIC, "com/gg/midway/asm/aop/demo/AddInterceptor", "beforeInvoke", "()V");
    }

    public void visitInsn(int opcode){
        if(opcode==RETURN){
            mv.visitMethodInsn(INVOKESTATIC, "com/gg/midway/asm/aop/demo/AddInterceptor", "afterInvoke", "()V");
        }
        super.visitInsn(opcode);
    }

}
