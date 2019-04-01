package com.gg.midway.aop.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * CGLIB 的例子，使用 POM 引入 cglib.jar
 */
public class SampleClass {
    public void test() {
        System.out.println("SampleClass.test() hello world");
    }

    public static void main(String[] args) throws InterruptedException {
        Enhancer enhancer = new Enhancer();
        // 扩展，Enhancer既能够代理普通的class，也能够代理接口
        enhancer.setSuperclass(SampleClass.class);

        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                System.out.println("method " + method.getName() + "() begin ...");
                //纳秒  1毫秒=1000纳秒
                long time = System.nanoTime();
                Object resultObj = proxy.invokeSuper(obj, args);
                System.out.println(method.getName() + "运行耗时：" + (System.nanoTime() - time) + "纳秒");
                System.out.println("method " + method.getName() + "() end ...");
                return resultObj;
            }
        });
        SampleClass sample = (SampleClass) enhancer.create();

        sample.test();
        Thread.sleep(1000);

        String str = "" + sample.hashCode();
        System.out.println("hashCode() ret : " + str);
        Thread.sleep(1000);

        // toString() 操作，会调用 hashCode()
        str = sample.toString();
        System.out.println("toString() " + sample.toString());
        Thread.sleep(1000);

        // 打印出 class 类名
        str = "" + sample.getClass();
        System.out.println("sample.getClass()  " + sample.toString());

    }
}
