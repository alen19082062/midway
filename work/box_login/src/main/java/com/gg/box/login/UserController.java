package com.gg.box.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Donghua.Chen on 2018/7/25.
 */
@RestController
public class UserController {

    @Autowired
    UserMapper userMapper;

    @GetMapping("get/{name}")
    public List<User> getUser(@PathVariable("name") String name) {
        return userMapper.findByName(name) ;
    }

    @GetMapping("all")
    public List<User> getUser() {
        return userMapper.findAll();
    }

}