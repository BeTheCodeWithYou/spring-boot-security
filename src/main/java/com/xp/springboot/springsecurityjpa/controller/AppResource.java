package com.xp.springboot.springsecurityjpa.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppResource {

    @GetMapping("/hello")
    public String hello(){
        return "Hello Spring security app";
    }
}
