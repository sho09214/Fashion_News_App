package com.example.Fashion_News_App.dto.web;

import lombok.Data;

@Data
public class NewsResponseDto {

    private Long id;
    private String title;
    private String description;
    private String url;
    private String imageUrl;
    private String sourceName;
    private String publishedAt;
    private String category;
    private boolean hasImage;

    public boolean getHasImage() {
        return imageUrl != null && !imageUrl.trim().isEmpty();
    }

}
