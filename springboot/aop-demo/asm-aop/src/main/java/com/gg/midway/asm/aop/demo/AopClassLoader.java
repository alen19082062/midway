package com.gg.midway.asm.aop.demo;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;

import java.io.File;
import java.io.InputStream;

public class AopClassLoader extends ClassLoader implements Opcodes {
    public AopClassLoader(ClassLoader parent) {
        super(parent);
    }

    public  Class<?> loadClass(String name) throws ClassNotFoundException {
        if (!name.contains("Account$Enhance") && !name.contains("Hi$Enhance"))
            return super.loadClass(name);
        try {
            ClassWriter cw = new ClassWriter(0);
            //
            String className = "com/gg/midway/asm/aop/demo/Account.class";
            File file = new File(className) ;
            System.out.println("loadClass() class length = " + file.length());

            // 修改前的长度，经过测试，修改前的长度 = class 文件长度
            InputStream is = Thread.currentThread().
                    getContextClassLoader().getResourceAsStream(className);
            byte[] buff = new byte[4096] ;
            int n = is.read(buff);
            System.out.println("loadClass() read length = " + n );

            is = Thread.currentThread().
                    getContextClassLoader().getResourceAsStream(className);
            ClassReader reader = new ClassReader(is);
            ClassVisitor cv = new AopClassAdapter(Opcodes.ASM7, cw);
            reader.accept( cv , ClassReader.SKIP_DEBUG);
            //
            byte[] code = cw.toByteArray();
            //            FileOutputStream fos = new FileOutputStream("c:\\TestBean_Tmp.class");
            //            fos.write(code);
            //            fos.flush();
            //            fos.close();
            System.out.println("loadClass() add method , name = " + name + ", class length = " + code.length);
            return this.defineClass(name, code, 0, code.length);
        } catch (Throwable e) {
            e.printStackTrace();
            throw new ClassNotFoundException();
        }
    }
}

