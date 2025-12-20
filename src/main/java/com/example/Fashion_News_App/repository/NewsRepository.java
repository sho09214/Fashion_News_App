package com.example.Fashion_News_App.repository;

import com.example.Fashion_News_App.entity.NewsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsRepository extends JpaRepository<NewsEntity, Long> {
}
