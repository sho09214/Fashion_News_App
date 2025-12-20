package com.example.Fashion_News_App.dto.web;

import lombok.Data;

@Data
public class UserResponseDto {

    private Long userId;
    private String email;

    public UserResponseDto(Long userId, String email) {
        this.userId = userId;
        this.email = email;
    }
}
