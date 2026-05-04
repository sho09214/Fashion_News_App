package com.example.Fashion_News_App.repository;

import com.example.Fashion_News_App.entity.NewsKeywordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsKeywordRepository extends JpaRepository<NewsKeywordEntity, Long> {
}
