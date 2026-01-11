package com.example.Fashion_News_App.service;

import com.example.Fashion_News_App.dto.web.NewsResponseDto;

import java.util.List;

public interface FavoriteNewsServiceIF {

    //お気に入り登録
    void addFavorite(Long userId, Long newsId);

    //お気に入り登録解除
    void deleteFavorite(Long userId, Long newsId);

    //お気に入りニュース取得
    List<NewsResponseDto> getFavoriteNews(Long userId);

}
