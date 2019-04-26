package com.gg.midway.asm.demo;

public class AccountTest {

    public static void main(String[] args)throws Exception
    {
        String fullName = Account.class.getName();
        String fullLoadName = fullName + "$Enhance" ;
        System.out.println("main() fullLoadName = " + fullLoadName );

        Class<?> clazz = new AopClassLoader(Thread.currentThread().getContextClassLoader()).loadClass(fullLoadName);
        clazz.getMethods()[0].invoke(clazz.newInstance());

    }
}
