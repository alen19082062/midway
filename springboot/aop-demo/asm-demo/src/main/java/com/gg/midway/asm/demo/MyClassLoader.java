package com.gg.midway.asm.demo;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;

import java.io.InputStream;

class MyClassLoader extends ClassLoader {

    public MyClassLoader(ClassLoader parent) {
        super(parent);
    }

    public Class<?> defineClass(String name, byte[] b) throws ClassNotFoundException {
        // ClassLoader是个抽象类，而ClassLoader.defineClass 方法是protected的
        // 所以我们需要定义一个子类将这个方法暴露出来
        // return super.defineClass(name, b, 0, b.length);

        if (!name.contains("Enhance")) {
            System.out.println("do nothing ");
            return super.loadClass(name);
        }
        try {
            System.out.println("load account  ");

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

