package com.example.Fashion_News_App.service;

import com.example.Fashion_News_App.dto.PasswordChangeDto;
import com.example.Fashion_News_App.dto.business.UserLoginBusinessDto;
import com.example.Fashion_News_App.dto.business.UserRegisterBusinessDto;
import com.example.Fashion_News_App.dto.web.UserCurrentResponseDto;
import com.example.Fashion_News_App.dto.web.UserLoginResponseDto;
import com.example.Fashion_News_App.dto.web.UserRegisterResponseDto;
import org.springframework.http.ResponseEntity;

public interface UserServiceIF {

    UserRegisterResponseDto register(UserRegisterBusinessDto businessDto);

    UserLoginResponseDto login(UserLoginBusinessDto businessDto);

    //退会
    void withdraw(Long userId);

    //パスワード変更
    void changePassword(Long userId, PasswordChangeDto passwordChangeDto);

    UserCurrentResponseDto getCurrentUser(Long userId);
}
