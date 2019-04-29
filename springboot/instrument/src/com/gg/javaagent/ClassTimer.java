package com.gg.javaagent;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;

import java.lang.instrument.ClassFileTransformer;
import java.security.ProtectionDomain;

public class ClassTimer implements ClassFileTransformer {
    @Override
    public byte[] transform(ClassLoader loader, String className,
                            Class<?> classBeingRedefined,
                            ProtectionDomain protectionDomain,
                            byte[] classfileBuffer) {
        // https://dormouse-none.github.io/2018-06-25-ASM-Core/
        ClassReader cr = new ClassReader(classfileBuffer);
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        MyClassWriter mcw = new MyClassWriter(Opcodes.ASM7, cw);
        cr.accept(mcw, ClassReader.EXPAND_FRAMES);
        return cw.toByteArray();
    }

}
