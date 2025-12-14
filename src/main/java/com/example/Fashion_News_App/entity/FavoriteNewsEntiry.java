package com.example.Fashion_News_App.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "favorite_news")
@Data
public class FavoriteNewsEntiry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @ManyToOne
//    @JoinColumn(name = "user_id", nullable = false)
//    private UserEntiry userEntiry;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private NewsEntity newsEntity;

    private LocalDateTime createdAt;
}
