package com.gg.midway.asm.demo;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;

import java.io.InputStream;

public class AopClassLoader extends ClassLoader implements Opcodes {
    public AopClassLoader(ClassLoader parent) {
        super(parent);
    }

    public  Class<?> loadClass(String name) throws ClassNotFoundException {
        if (!name.contains("Account$Enhance"))
            return super.loadClass(name);
        try {
            ClassWriter cw = new ClassWriter(0);
            //
            InputStream is = Thread.currentThread().
                    getContextClassLoader().
                    getResourceAsStream("com/gg/midway/asm/demo/Account.class");
            ClassReader reader = new ClassReader(is);
            ClassVisitor cv = new AddSecurityCheckClassAdapter(Opcodes.ASM7, cw);
            reader.accept( cv , ClassReader.SKIP_DEBUG);
            //
            byte[] code = cw.toByteArray();
            //            FileOutputStream fos = new FileOutputStream("c:\\TestBean_Tmp.class");
            //            fos.write(code);
            //            fos.flush();
            //            fos.close();
            System.out.println("loadClass() name = " + name);
            return this.defineClass(name, code, 0, code.length);
        } catch (Throwable e) {
            e.printStackTrace();
            throw new ClassNotFoundException();
        }
    }
}

