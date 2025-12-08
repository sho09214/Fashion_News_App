package com.example.Fashion_News_App.controller;

import com.example.Fashion_News_App.dto.web.NewsResponseDto;
import com.example.Fashion_News_App.service.NewsServiceIF;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/news")
@RequiredArgsConstructor
public class NewsController {

    private final NewsServiceIF newsServiceIF;

    @GetMapping
    public List<NewsResponseDto> getAllNews() {
        return newsServiceIF.getAllNews();
    }
}
