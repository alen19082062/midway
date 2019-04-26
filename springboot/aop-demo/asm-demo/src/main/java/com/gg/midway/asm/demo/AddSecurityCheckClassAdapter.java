package com.gg.midway.asm.demo;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;

import static jdk.internal.org.objectweb.asm.Opcodes.*;
import static org.objectweb.asm.Opcodes.ASM7;

public class AddSecurityCheckClassAdapter extends ClassVisitor{

    String  enhancedSuperName = "defalut" ;
    public AddSecurityCheckClassAdapter(int api,ClassVisitor cv) {
        super(ASM7,cv);
    }

//    public void visit(final int version, final int access, final String name,
//                      final String signature, final String superName,
//                      final String[] interfaces) {
//        String enhancedName = name + "$Enhance";  // 改变类命名
//        enhancedSuperName = name; // 改变父类，这里是”Account”
//        System.out.println("visit() enhancedSuperName : " + enhancedSuperName);
//        System.out.println("visit() enhancedName : " + enhancedName);
//        super.visit(version, access, enhancedName, signature,
//                enhancedSuperName, interfaces);
//    }
//
//    public MethodVisitor visitMethod(final int access, final String name,
//                                     final String desc, final String signature, final String[] exceptions) {
//        MethodVisitor mv = cv.visitMethod(access, name, desc, signature, exceptions);
//        MethodVisitor wrappedMv = mv;
//        System.out.println("visitMethod() name : " + name);
//        if (mv != null) {
//            if (name.equals("operation")) {
//                System.out.println("visitMethod() operation method , add check method  " );
//                wrappedMv = new AddSecurityCheckMethodAdapter(mv);
//            }
//            else if (name.equals("<init>")) {
//                wrappedMv = new ChangeToChildConstructorMethodAdapter(mv,
//                        enhancedSuperName);
//            }
//        }
//        return wrappedMv;
//    }

    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        //更改类名，并使新类继承原有的类。
        super.visit(version, access, name + "$Enhance", signature, name, interfaces);
        {
            MethodVisitor mv = super.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
            mv.visitCode();
            mv.visitVarInsn(ALOAD, 0);
            mv.visitMethodInsn(INVOKESPECIAL, name, "<init>", "()V");
            mv.visitInsn(RETURN);
            mv.visitMaxs(1, 1);
            mv.visitEnd();
        }
    }

    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        if ("<init>".equals(name))
            return null;
        if (!name.equals("sayHello") && !name.equals("operation"))
            return null;
        //
        MethodVisitor mv = super.visitMethod(access, name, desc, signature, exceptions);
        return new AddSecurityCheckMethodAdapter(mv);
    }

}
