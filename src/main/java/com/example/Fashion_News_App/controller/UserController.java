package com.example.Fashion_News_App.controller;

import com.example.Fashion_News_App.dto.business.UserLoginBusinessDto;
import com.example.Fashion_News_App.dto.business.UserRegisterBusinessDto;
import com.example.Fashion_News_App.dto.web.UserLoginResponseDto;
import com.example.Fashion_News_App.dto.web.UserRegisterResponseDto;
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

    //新規会員登録
    @PostMapping("/register")
    public UserRegisterResponseDto register(@RequestBody UserRegisterBusinessDto businessDto) {
        return userServiceIF.register(businessDto);
    }

    //ログイン
    @PostMapping("/login")
    public UserLoginResponseDto login(@RequestBody UserLoginBusinessDto businessDto) {
        return userServiceIF.login(businessDto);
    }

    //退会

    //パスワード変更

}
