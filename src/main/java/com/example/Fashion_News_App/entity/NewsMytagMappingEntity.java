package com.example.Fashion_News_App.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "news_mytag_mappings")
@Data
public class NewsMytagMappingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    //mapping → News
    @ManyToOne
    @JoinColumn(name = "news_id")
    private NewsViewEntity newsViewEntity;

    //mapping → Mytag
    @ManyToOne
    @JoinColumn(name = "mytag_id")
    private MytagEntity myTagEntity;

    private Long userId;
}
