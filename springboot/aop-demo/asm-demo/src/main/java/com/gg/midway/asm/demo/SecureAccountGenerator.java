package com.gg.midway.asm.demo;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;

import java.io.IOException;

import static org.objectweb.asm.Opcodes.ASM7;

public class SecureAccountGenerator {
    // private static AccountGeneratorClassLoader classLoader =
    //        new AccountGeneratorClassLoader();

//    private static MyClassLoader classLoader =
//            new MyClassLoader();
//
//    private static Class secureAccountClass;
//
//    public Account generateSecureAccount() throws ClassFormatError,
//            InstantiationException, IllegalAccessException, IOException {
//
//        System.out.println("generateSecureAccount() secureAccountClass = " + secureAccountClass);
//        if (null == secureAccountClass) {
//
//            String fullName = Account.class.getName();
//            String fullNameType = fullName.replace(".", "/");
//            System.out.println("generateSecureAccount() fullNameType = " + fullNameType);
//
//            ClassReader cr = new ClassReader(fullNameType);
//            ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
//            ClassVisitor classAdapter = new AddSecurityCheckClassAdapter(ASM7,cw);
//            cr.accept(classAdapter, ClassReader.SKIP_DEBUG);
//            byte[] enhanced  = cw.toByteArray();
//            System.out.println("generateSecureAccount() class file size = " + enhanced.length);
//            secureAccountClass = classLoader.defineClass(
//                    "com/gg/midway/asm/demo/Account2",enhanced);
//        }
//        Account account = (Account) secureAccountClass.newInstance() ;
//        System.out.println("generateSecureAccount() account : " + account);
//        return account;
//    }
//
//    private static class AccountGeneratorClassLoader extends ClassLoader {
//        public Class defineClassFromClassFile(String className,
//                                              byte[] classFile) throws ClassFormatError {
//
//            return super.defineClass(className , classFile, 0,
//                    classFile.length);
//        }


}
