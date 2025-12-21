package com.example.Fashion_News_App.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.Authenticator;

@RestController
@RequestMapping("/api/debug")
public class DebugController {

    @PostMapping("/user/test")
    public  String test(Authentication authentication) {
        return "userId = " + authentication.getPrincipal();
    }
}
