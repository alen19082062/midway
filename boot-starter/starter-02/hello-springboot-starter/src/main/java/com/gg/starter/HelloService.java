package com.gg.starter;

public class HelloService {

    HelloProperties helloProperties;

    public String hello() {
        return "hello name : " + helloProperties.getName();
    }

    public HelloProperties getHelloProperties() {
        return helloProperties;
    }

    public void setHelloProperties(HelloProperties helloProperties) {
        this.helloProperties = helloProperties;
    }
}
