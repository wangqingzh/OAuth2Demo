package com.example.xxx.web.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestApi {
    @GetMapping("admin/test")
    public Object testAdmin(){
        return new String("aoligei");
    }

    @GetMapping("uset/test")
    public Object testUser(){
        return new String("aoligei");
    }
}
