package com.gg;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;

/**
 * 项目中遇到model的String类型的属性被赋予空字符串””,希望把它替换为null
 */
public class UserTest {

    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        Class<? extends User> loaded = new ByteBuddy()
                .subclass(User.class)
                // .method(ElementMatchers.isSetter().and(ElementMatchers.takesArguments(String.class)))
                .method(ElementMatchers.hasMethodName("setName").and(ElementMatchers.takesArguments(String.class)))
                // .method(ElementMatchers.takesArguments(String.class))
                .intercept(MethodDelegation.to(Intercept.class))
                .make()
                .load(UserTest.class.getClassLoader())
                .getLoaded();

        User user = loaded.newInstance();
        user.setAge(11);
        user.setName("gude");
        System.out.println(user);

        User user1 = loaded.newInstance();
        user1.setName("");
        user1.setAge(1);
        System.out.println(user1);
    }
}
