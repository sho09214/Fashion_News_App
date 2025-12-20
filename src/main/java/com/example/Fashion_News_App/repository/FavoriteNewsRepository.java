package com.example.Fashion_News_App.repository;

import com.example.Fashion_News_App.entity.FavoriteNewsEntiry;
import com.example.Fashion_News_App.entity.NewsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FavoriteNewsRepository extends JpaRepository<FavoriteNewsEntiry, Long> {

    //すでにお気に入り登録か判定
//    Optional<FavoriteNewsEntiry> findByUserAndNews(UserEntity userEntity, NewsEntity newsEntity);
}
