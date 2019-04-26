package com.gg.midway.asm.demo;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import java.io.File;
import java.io.FileOutputStream;

public class Generator{

    public static void main(String[] args) throws Exception {
        ClassReader cr = new ClassReader("com/gg/midway/asm/demo/Account");
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
        ClassVisitor cv = new AddSecurityCheckClassAdapter(Opcodes.ASM7, cw);
        cr.accept(cv, ClassReader.SKIP_DEBUG);
        System.out.println("load class Account");
        byte[] data = cw.toByteArray();
        File file = new File("Account2.class");
        FileOutputStream fout = new FileOutputStream(file);
        fout.write(data);
        fout.close();
    }
}