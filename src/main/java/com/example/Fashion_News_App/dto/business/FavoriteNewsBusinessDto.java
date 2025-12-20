package com.example.Fashion_News_App.dto.business;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FavoriteNewsBusinessDto {

    private Long id;
    private Long userId;
    private Long newsId;
    private LocalDateTime createdAt;
}
