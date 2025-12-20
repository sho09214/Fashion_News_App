package com.example.Fashion_News_App.service;

import com.example.Fashion_News_App.dto.business.UserLoginBusinessDto;
import com.example.Fashion_News_App.dto.business.UserRegisterBusinessDto;
import com.example.Fashion_News_App.dto.web.UserLoginResponseDto;
import com.example.Fashion_News_App.dto.web.UserRegisterResponseDto;

public interface UserServiceIF {

    UserRegisterResponseDto register(UserRegisterBusinessDto businessDto);

    UserLoginResponseDto login(UserLoginBusinessDto businessDto);
}
