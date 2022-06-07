package com.example.carros.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class IndexController {

    @GetMapping
    public String get(){
        return "Api de carros iniciada!";
    }

    @GetMapping("/userinfo")
    public UserDetails userDetails (@AuthenticationPrincipal UserDetails user){
        return user;
    }
}
