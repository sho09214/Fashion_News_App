package com.example.Fashion_News_App.service;

import com.example.Fashion_News_App.dto.web.MytagResponseDto;
import com.example.Fashion_News_App.dto.web.NewsResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface NewsServiceIF {

    //ニュース全件取得
    List<NewsResponseDto> getAllNews(Long userId);

    //ログインユーザーのマイタグに紐づくニュース一覧
    List<NewsResponseDto> getNewsByUserMytags(Long userId);
}
