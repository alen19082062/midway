package com.gg;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.matcher.ElementMatchers;
import org.junit.Assert;
import org.junit.Test;

// 改写 toString() 的输出值
public class ByteBuddyTest3 {

    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        Class<?> dynamicType = new ByteBuddy()
                .subclass(Object.class)
                .method(ElementMatchers.named("toString"))
                .intercept(FixedValue.value("Hello ByteBuddy World!"))
                .make()
                .load(ByteBuddyTest3.class.getClassLoader())
                .getLoaded();
        String str = dynamicType.newInstance().toString();
        System.out.println(str);
        // Assert.assertEquals("toString方法改写错误", str, "Hello World!");
    }
}
