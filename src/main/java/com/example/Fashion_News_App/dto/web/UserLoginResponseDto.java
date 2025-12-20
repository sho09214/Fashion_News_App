package com.example.Fashion_News_App.dto.web;

import lombok.Data;

@Data
public class UserLoginResponseDto {
    private String token;
    private Long userId;
    private String email;

    public UserLoginResponseDto(String token, Long userId, String email) {
        this.token = token;
        this.userId = userId;
        this.email = email;
    }
}
