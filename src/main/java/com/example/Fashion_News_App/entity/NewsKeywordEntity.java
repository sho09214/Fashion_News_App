package com.example.Fashion_News_App.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "news_keywords")
public class NewsKeywordEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long newsId;

    private String keyword;

    private String keywordType;
}
