package com.example.Fashion_News_App.service;
import com.example.Fashion_News_App.entity.FavoriteNewsEntity;
import com.example.Fashion_News_App.repository.FavoriteNewsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class FavoriteNewsServiceImpl implements  FavoriteNewsServiceIF{

    private final FavoriteNewsRepository favoriteNewsRepository;

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
}
