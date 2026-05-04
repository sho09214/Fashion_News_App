package com.example.Fashion_News_App.dto.business;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class NewsBusinessDto {

    private Long id;
    private String title;
    private String description;
    private String url;
    private String urlToImage;
    private String sourceName;
    private LocalDateTime publishedAt;
    private String summary;

    //紐づくMytag一覧
    private List<String> myTags;
}
