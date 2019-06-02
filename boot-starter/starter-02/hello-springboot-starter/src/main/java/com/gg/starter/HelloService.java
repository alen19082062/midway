package com.gg.starter;

public class HelloService {
    HelloProperties helloProperties;
    private String msg;
    private String name;

    public String hello() {
        System.out.println("hello() name: " + helloProperties.getName() );
        System.out.println("hello() msg: " + helloProperties.getMsg() );
        return "hello name : " + helloProperties.getName();
    }

    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public HelloProperties getHelloProperties() {
        return helloProperties;
    }

    public void setHelloProperties(HelloProperties helloProperties) {
        this.helloProperties = helloProperties;
    }
}
