package com.example.Fashion_News_App.controller;

import com.example.Fashion_News_App.dto.web.NewsResponseDto;
import com.example.Fashion_News_App.service.NewsServiceIF;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/news")
@RequiredArgsConstructor
public class NewsController {

    private final NewsServiceIF newsServiceIF;

    //ニュース取得
    @GetMapping
    public List<NewsResponseDto> getAllNews(Authentication authentication) {
        Long userId = null;

        if(authentication != null && authentication.isAuthenticated()) {
            userId = (Long) authentication.getPrincipal();
        }

        return newsServiceIF.getAllNews(userId);
    }
}
