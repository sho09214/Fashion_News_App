package com.example.Fashion_News_App.dto.web;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FavoriteNewsResponseDto {

    private Long id;
    private Long userId;
    private Long newsId;
    private String createdAt;
}
