package com.example.Fashion_News_App.service;

import com.example.Fashion_News_App.dto.web.NewsResponseDto;
import com.example.Fashion_News_App.entity.NewsEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TopServiceIF {

    //全件取得
    List<NewsResponseDto> getAllNews();
}
