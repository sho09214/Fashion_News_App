package com.example.Fashion_News_App.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "news")
@Data
public class NewsEntity {

    @Id
    private Long id;

    private String sourceId;
    private String sourceName;
    private String author;
    private String title;
    private String description;
    private String url;
    private String urlToImage;
    private LocalDateTime publishedAt;
    private String summary;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String hash;

    // News → 中間テーブル
    @OneToMany(mappedBy = "newsEntity")
    private List<NewsMytagMappingEntity> newsMytagMappingEntitys = new ArrayList<>();
}
