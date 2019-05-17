package com.gg;

public class User {
    // name 默认值为 null
    private String name ;
    private Integer age;

    public User(){
    }

    public User(String name , int age){
        this.name= name ;
        this.age = age ;
    }

    public void setName(String name){
        System.out.println("setName() name = "+ name);
        this.name = name ;
    }

    public void setAge(int age){
        this.age = age ;
    }

    @Override
    public String toString() {
        return "Name/Age: " + this.name + "/" + this.age;
    }

}
