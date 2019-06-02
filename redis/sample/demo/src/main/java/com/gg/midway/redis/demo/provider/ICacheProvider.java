package com.gg.midway.redis.demo.provider;

public interface ICacheProvider {
    void setString(String key, String value);

    String getString(String key);
}
