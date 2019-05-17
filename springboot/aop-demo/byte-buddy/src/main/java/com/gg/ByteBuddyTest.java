package com.gg;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.NamingStrategy;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.matcher.ElementMatchers;

public class ByteBuddyTest {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        DynamicType.Unloaded<?> dynamicType = new ByteBuddy()
                .with(new NamingStrategy.AbstractBase() {
                    @Override
                    protected String name(TypeDescription typeDescription) {
                        System.out.println("name() typeDescription : " + typeDescription);
                        System.out.println("name() typeDescription.getSimpleName() : " + typeDescription.getSimpleName());
                        return "I.love.ByteBuddy." + typeDescription.getSimpleName();
                    }

                })
                .subclass(Object.class)
              //   .name("class_1_create_by_bytebuddy")
                .make();

        DynamicType.Unloaded<?> dynamicType2 = new ByteBuddy()
                .with(new NamingStrategy.AbstractBase() {
                    @Override
                    protected String name(TypeDescription typeDescription) {
                        System.out.println("name() typeDescription : " + typeDescription);
                        return "i.love.ByteBuddy." + typeDescription.getSimpleName();
                    }

                })
                .subclass(Object.class)
                .name("class_2_create_by_bytebuddy")
                .make();

        System.out.println("dynamicType --> ");
        System.out.println(dynamicType.getTypeDescription().getCanonicalName());
        System.out.println("dynamicType2 --> ");
        System.out.println(dynamicType2.getTypeDescription().getCanonicalName());

        ByteBuddy byteBuddy3 = new ByteBuddy();
        byteBuddy3.with(new NamingStrategy.SuffixingRandom("class_3_suffix"));
        DynamicType.Unloaded<?> dynamicType3 = byteBuddy3.subclass(Object.class).make();
        System.out.println("dynamicType3 --> ");
        System.out.println(dynamicType3.getTypeDescription().getCanonicalName());

        ByteBuddy byteBuddy4 = new ByteBuddy();
        DynamicType.Unloaded<?> dynamicType4 =
                byteBuddy4.with(new NamingStrategy.SuffixingRandom("class_4_suffix"))
                        .subclass(Object.class)
                        .make();
        System.out.println("dynamicType4 --> ");
        System.out.println(dynamicType4.getTypeDescription().getCanonicalName());

        ByteBuddy byteBuddy5 = new ByteBuddy();
        ByteBuddy suffix = byteBuddy5.with(new NamingStrategy.SuffixingRandom("class_5_suffix"));
        DynamicType.Unloaded<?> dynamicType5 = suffix.
                subclass(Object.class).make();
        System.out.println("dynamicType5 --> ");
        System.out.println(dynamicType5.getTypeDescription().getCanonicalName());

        Class<?> dynamicType6 = new ByteBuddy()
                .subclass(Object.class)
                .method(ElementMatchers.named("toString"))
                .intercept(FixedValue.value("Hello ByteBuddyWorld!"))
                .make()
                .load(ByteBuddyTest.class.getClassLoader())
                .getLoaded();
        System.out.println("dynamicType6 --> ");
        System.out.println("dynamicType6.getCanonicalName() : " + dynamicType6.getCanonicalName());
        String str = dynamicType6.newInstance().toString();
        System.out.println(str);
        // Assert.assertEquals("toString方法改写错误", str, "Hello World!");
    }

}
