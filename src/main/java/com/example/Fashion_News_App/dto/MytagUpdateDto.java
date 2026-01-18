package com.example.Fashion_News_App.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MytagUpdateDto {

    private Long id;
    private String tagName;
    private String color;
    private LocalDateTime updatedAt;
}
