package com.example.Fashion_News_App.dto.web;

import lombok.Data;

@Data
public class UserRegisterResponseDto {

    private Long userId;
    private String email;

    public UserRegisterResponseDto(Long userId, String email) {
        this.userId = userId;
        this.email = email;
    }
}
