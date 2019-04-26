package com.gg.midway.asm.aop.demo;

public class Hi {
    public void sayHello() {
        try {
            System.out.println("sayHello() begin ....  ");
            Thread.sleep(2 * 1000);
            System.out.println("sayHello() end ... ");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args)throws Exception
    {
        String fullName = Account.class.getName();
        String fullLoadName = fullName + "$Enhance" ;
        // com.gg.midway.asm.aop.demo.Hi$Enhance
        Class<?> clazz = new AopClassLoader(Thread.currentThread().getContextClassLoader()).loadClass(fullLoadName);
        // Class<?> clazz = new AopClassLoader(Thread.currentThread().getContextClassLoader()).loadClass("com.gg.midway.asm.aop.demo.Hi$EnhanceByASM");
        clazz.getMethods()[0].invoke(clazz.newInstance());

//        fullName = Hi.class.getName();
//        fullLoadName = fullName + "$Enhance" ;
//        // com.gg.midway.asm.aop.demo.Hi$Enhance
//        clazz = new AopClassLoader(Thread.currentThread().getContextClassLoader()).loadClass(fullLoadName);
//        // Class<?> clazz = new AopClassLoader(Thread.currentThread().getContextClassLoader()).loadClass("com.gg.midway.asm.aop.demo.Hi$EnhanceByASM");
//        clazz.getMethods()[0].invoke(clazz.newInstance());

    }

}
