package com.example.Fashion_News_App.repository;

import com.example.Fashion_News_App.entity.FavoriteNewsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteNewsRepository extends JpaRepository<FavoriteNewsEntity, Long> {

//    すでにお気に入り登録か判定
    boolean existsByUserIdAndNewsId(Long userId, Long newsId);
}
