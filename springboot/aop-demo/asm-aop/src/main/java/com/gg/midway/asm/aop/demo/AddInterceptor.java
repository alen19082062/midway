package com.gg.midway.asm.aop.demo;

// 在原有函数上添加的方法，现在只能是静态方法
public class AddInterceptor {
    public static void beforeInvoke() {
        System.out.println("AddInterceptor.beforeInvoke() before .....");
    };

    public static void afterInvoke() {
        System.out.println("AddInterceptor.beforeInvoke() after .....");
    };
}
