package com.example.Fashion_News_App.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "favorite_news")
@Data
public class FavoriteNewsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private Long newsId;

    private LocalDateTime createdAt;
}
