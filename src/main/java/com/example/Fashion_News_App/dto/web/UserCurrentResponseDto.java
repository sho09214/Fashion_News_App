package com.example.Fashion_News_App.dto.web;

import lombok.Data;

@Data
public class UserCurrentResponseDto {

    private Long userId;
    private String email;
    private String name;

    public UserCurrentResponseDto(){}

    public UserCurrentResponseDto(Long userId, String email, String name) {
        this.userId = userId;
        this.email = email;
        this.name = name;
    }
}
