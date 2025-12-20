package com.example.Fashion_News_App.controller;

import com.example.Fashion_News_App.dto.business.UserBusinessDto;
import com.example.Fashion_News_App.dto.web.UserResponseDto;
import com.example.Fashion_News_App.service.UserServiceIF;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceIF userServiceIF;

    @PostMapping("/register")
    public UserResponseDto register(@RequestBody UserBusinessDto businessDto) {
        return userServiceIF.register(businessDto);
    }

}
