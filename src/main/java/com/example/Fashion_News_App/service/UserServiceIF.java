package com.example.Fashion_News_App.service;

import com.example.Fashion_News_App.dto.business.UserBusinessDto;
import com.example.Fashion_News_App.dto.web.UserResponseDto;

public interface UserServiceIF {

    UserResponseDto register(UserBusinessDto businessDto);
}
