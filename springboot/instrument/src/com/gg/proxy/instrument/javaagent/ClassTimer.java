package com.gg.proxy.instrument.javaagent;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;

import java.lang.instrument.ClassFileTransformer;
import java.security.ProtectionDomain;

public class ClassTimer implements ClassFileTransformer {
    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
                            ProtectionDomain protectionDomain, byte[] classfileBuffer) {
        // 这里涉及到了 ASM 的内容，主要目的是向每个方法块的开始及方法块的结束部分插入与计时器有关的代码
        // 如果想了解 ASM 的内容，请参阅 https://dormouse-none.github.io/2018-06-25-ASM-Core/
        // 提供了一些基础性的内容，更多的请自行学习
        // 不了解具体内容将不影响对主体内容的理解
        ClassReader cr = new ClassReader(classfileBuffer);
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        MyClassWriter mcw = new MyClassWriter(Opcodes.ASM6, cw);
        cr.accept(mcw, ClassReader.EXPAND_FRAMES);
        return cw.toByteArray();
    }

}
