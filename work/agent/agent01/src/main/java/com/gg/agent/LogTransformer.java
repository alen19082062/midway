package com.gg.agent;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;

import java.lang.instrument.ClassFileTransformer;

import java.io.IOException;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

public class LogTransformer implements ClassFileTransformer {
    @Override
    public byte[] transform(ClassLoader loader, String className,
                            Class<?> classBeingRedefined,
                            ProtectionDomain protectionDomain,
                            byte[] classfileBuffer) {
            ClassReader cr= new ClassReader(classfileBuffer);
            ClassWriter cw=new ClassWriter(ClassWriter.COMPUTE_MAXS);
            TimeCountAdpter timeCountAdpter=new TimeCountAdpter(cw);
            cr.accept(timeCountAdpter,ClassReader.EXPAND_FRAMES);
            return cw.toByteArray();
    }

}
