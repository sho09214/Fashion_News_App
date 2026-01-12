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

    public MytagResponseDto(Long id, String tagName, String color, int displayOrder) {
        this.id = id;
        this.tagName = tagName;
        this.color = color;
        this.displayOrder = displayOrder;
    }

    public MytagResponseDto() {

    }
}
