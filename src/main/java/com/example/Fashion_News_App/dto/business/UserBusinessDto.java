package com.example.Fashion_News_App.dto.business;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserBusinessDto {

    private Long id;

    private String email;
    private String loginId;
    private String passwordHash;
    private String name;
    private Boolean isActive = true;
    private LocalDateTime lastLoginAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
