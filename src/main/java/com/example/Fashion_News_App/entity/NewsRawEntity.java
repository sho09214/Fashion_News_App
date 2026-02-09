package com.example.Fashion_News_App.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "news_raw")
@Data
public class NewsRawEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 100)
    private String sourceName;

    @Column(length = 500)
    private String title;

    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String rawContent;

    @Column(length = 1000)
    private String url;

    private LocalDateTime publishedAt;

    private LocalDateTime fetchedAt;

    @Column(length = 10)
    private String language;

    @Column(length = 10)
    private String country;

    @Column(length = 64, unique = true)
    private String hash;

    private LocalDateTime createdAt = LocalDateTime.now();
}
