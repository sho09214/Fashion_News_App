package com.example.Fashion_News_App.dto.web;

import lombok.Data;

import java.util.List;

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

    //紐づくMytag一覧
    private List<String> myTags;

    //お気に入りニュースか
    private boolean isFavorite;

}
