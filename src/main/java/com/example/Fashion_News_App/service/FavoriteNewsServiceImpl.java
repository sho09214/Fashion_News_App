package com.example.Fashion_News_App.service;
import com.example.Fashion_News_App.entity.FavoriteNewsEntity;
import com.example.Fashion_News_App.repository.FavoriteNewsRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class FavoriteNewsServiceImpl implements  FavoriteNewsServiceIF{

    private final FavoriteNewsRepository favoriteNewsRepository;

    //お気に入り登録
    @Override
    public void addFavorite(Long userId, Long newsId) {

        //二重登録防止
        if (favoriteNewsRepository.existsByUserIdAndNewsId(userId, newsId)) {
            return;
        }

        FavoriteNewsEntity favoriteNewsEntity = new FavoriteNewsEntity();
        favoriteNewsEntity.setUserId(userId);
        favoriteNewsEntity.setNewsId(newsId);
        favoriteNewsEntity.setCreatedAt(LocalDateTime.now());

        favoriteNewsRepository.save(favoriteNewsEntity);
    }

    //お気に入り登録解除
    @Override
    @Transactional
    public void deleteFavorite(Long userId, Long newsId) {

        //お気に入りされていなかったら例外
        if (!favoriteNewsRepository.existsByUserIdAndNewsId(userId, newsId)) {
            throw new RuntimeException("お気に入り登録されていません");
        }

        favoriteNewsRepository.deleteByUserIdAndNewsId(userId, newsId);
    }
}
