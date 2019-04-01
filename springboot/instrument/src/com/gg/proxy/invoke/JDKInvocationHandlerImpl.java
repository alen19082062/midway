package com.gg.proxy.invoke;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * JDK动态代理类
 */
public class JDKInvocationHandlerImpl implements InvocationHandler {

    /** 目标对象 */
    private Object targetObject;

    public JDKInvocationHandlerImpl(Object target){
        this.targetObject = target;
    }

    /**
     * 将目标对象传入进行代理
     */
    public Object newProxy(Object targetObject) {
        this.targetObject = targetObject;
        //返回代理对象
        return Proxy.newProxyInstance(targetObject.getClass().getClassLoader(),
                targetObject.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("invoke() ------ 插入前置通知代码 ------------");
        // 执行相应的目标方法
        Object rs = method.invoke(targetObject,args);
        System.out.println("invoke() ------ 插入后置处理代码 -------------");
        return rs;
    }

    /**
     * 模拟检查权限的例子
     */
    private void checkPopedom() {
        System.out.println("checkPopedom() ====== 检查权限 ======");
    }

}

