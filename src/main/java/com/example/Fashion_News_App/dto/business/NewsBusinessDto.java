package com.example.Fashion_News_App.dto.business;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class NewsBusinessDto {

    private Long id;
    private String title;
    private String description;
    private String url;
    private String urlToImage;
    private String sourceName;
    private LocalDateTime publishedAt;
    private String category;
}
