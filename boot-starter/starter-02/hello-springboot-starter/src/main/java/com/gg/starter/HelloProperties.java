package com.gg.starter;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "hello")
public class HelloProperties {

    private static final String DEFAULT_NAME = "golden";
    private static final String DEFAULT_MSG = "高登";
    private String name = DEFAULT_NAME;
    private String msg = DEFAULT_MSG;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
}
