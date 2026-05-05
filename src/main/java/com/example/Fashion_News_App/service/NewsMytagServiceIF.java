package com.example.Fashion_News_App.service;

public interface NewsMytagServiceIF {

    //ニュースにマイタグを追加
    void addTagToNews(Long userId, Long newsId, String tagName);

    //ニュースからマイタグを削除
    void removeTagFromNews(Long userId, Long newsId, String tagName);
}
