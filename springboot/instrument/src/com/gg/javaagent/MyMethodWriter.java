package com.gg.javaagent;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * @author fangfeng
 * @since 2018/8/7
 */
public class MyMethodWriter extends MethodVisitor {

    private String identifier;

    public MyMethodWriter(int api) {
        super(api);
    }

    public MyMethodWriter(int api, MethodVisitor methodVisitor) {
        super(api, methodVisitor);
    }

    public MyMethodWriter(int api, MethodVisitor methodVisitor, String className, String methodName, String descriptor) {
        super(api, methodVisitor);
        this.identifier = className + "." + methodName + " " + descriptor;
    }

    @Override
    public void visitCode() {
        super.visitCode();
        super.visitLdcInsn(identifier);
        super.visitMethodInsn(Opcodes.INVOKESTATIC, "com/gg/javaagent/StaticTimer", "timing", "(Ljava/lang/String;)V");
    }

    @Override
    public void visitInsn(int opcode) {
        if (172 <= opcode && opcode <= 177) {
            super.visitLdcInsn(identifier);
            super.visitMethodInsn(Opcodes.INVOKESTATIC, "com/gg/javaagent/StaticTimer", "timing", "(Ljava/lang/String;)V");
        }
        super.visitInsn(opcode);
    }
}
