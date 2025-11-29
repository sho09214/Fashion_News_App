package com.example.Fashion_News_App.dto.web;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MytagResponseDto {

    private Long id;
    private Long userId;
    private String tagName;
    private String color;
    private int displayOrder;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
