package com.example.Fashion_News_App.service;

import com.example.Fashion_News_App.dto.web.FavoriteNewsResponseDto;

public interface FavoriteNewsServiceIF {

    //お気に入り登録
    void addFavorite(Long userId, Long newsId);

}
