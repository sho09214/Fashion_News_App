package com.example.Fashion_News_App.service;

import com.example.Fashion_News_App.dto.web.NewsResponseDto;
import com.example.Fashion_News_App.entity.NewsEntity;
import com.example.Fashion_News_App.mapper.NewsMapper;
import com.example.Fashion_News_App.repository.TopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TopServiceImpl implements TopServiceIF {

    @Autowired
    private TopRepository topRepository;

    @Autowired
    private NewsMapper newsMapper;

    //全件取得
    @Override
    public List<NewsResponseDto> getAllNews() {
        //Entity → Business Dto → Response DTO

        return topRepository.findAll().stream()
                .map(newsMapper::toBusinessDto)
                .map(newsMapper::toResponseDto)
                .collect(Collectors.toList());
    }
}
