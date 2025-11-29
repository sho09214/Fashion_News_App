package com.example.Fashion_News_App.service;

import com.example.Fashion_News_App.dto.web.MytagResponseDto;
import com.example.Fashion_News_App.dto.web.NewsResponseDto;
import com.example.Fashion_News_App.mapper.MytagMapper;
import com.example.Fashion_News_App.mapper.NewsMapper;
import com.example.Fashion_News_App.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NewsServiceImpl implements NewsServiceIF {

    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private NewsMapper newsMapper;

    @Autowired
    private MytagMapper mytagMapper;

    //ニュース全件取得
    @Override
    public List<NewsResponseDto> getAllNews() {
        //Entity → Business Dto → Response DTO
        return newsRepository.findAll().stream()
                .map(newsMapper::toBusinessDto)
                .map(newsMapper::toResponseDto)
                .collect(Collectors.toList());
    }

}
