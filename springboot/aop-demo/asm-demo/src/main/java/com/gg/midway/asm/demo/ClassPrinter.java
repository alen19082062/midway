package com.gg.midway.asm.demo;

import org.objectweb.asm.*;

import java.io.IOException;

import static org.objectweb.asm.Opcodes.ASM7;

// 打印class字节码内容的类信息
public class ClassPrinter extends ClassVisitor {
    public ClassPrinter() throws IOException {
        super(ASM7);
    }
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        System.out.println(name + " extends " + superName + " {");
    }

    public void visitSource(String source, String debug) {}

    public void visitOuterClass(String owner, String name, String desc) {}

    public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
        return null;
    }

    public void visitAttribute(Attribute attr) {}
    public void visitInnerClass(String name, String outerName, String innerName, int access) {}

    public FieldVisitor visitField(int access, String name, String desc, String signature, Object value) {
        System.out.println(" " + desc + " " + name);
        return null;
    }

    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        System.out.println(" " + name + desc);
        return null;
    }
    public void visitEnd() {
        System.out.println("}");
    }

    public static void main(String[] args) throws Exception {
        //ClassReader作为字节码生产者，ClassPrinter作为字节码消费者
        ClassPrinter cp = new ClassPrinter();

        System.out.println("======== java.lang.Runnable  =====");
        ClassReader cr = new ClassReader("java.lang.Runnable");
        cr.accept(cp, 0);

        System.out.println("======== java.io.BufferedReader  =====");
        cr = new ClassReader("java.io.BufferedReader");
        cr.accept(cp, 0);
    }
}