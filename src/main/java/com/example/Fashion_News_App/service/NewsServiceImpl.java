package com.example.Fashion_News_App.service;

import com.example.Fashion_News_App.dto.business.NewsBusinessDto;
import com.example.Fashion_News_App.dto.web.MytagResponseDto;
import com.example.Fashion_News_App.dto.web.NewsResponseDto;
import com.example.Fashion_News_App.entity.NewsEntity;
import com.example.Fashion_News_App.mapper.MytagMapper;
import com.example.Fashion_News_App.mapper.NewsMapper;
import com.example.Fashion_News_App.repository.FavoriteNewsRepository;
import com.example.Fashion_News_App.repository.NewsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsServiceIF {

    private final NewsRepository newsRepository;
    private final NewsMapper newsMapper;
    private final MytagMapper mytagMapper;
    private final FavoriteNewsRepository favoriteNewsRepository;

    //ニュース全件取得
    @Override
    public List<NewsResponseDto> getAllNews(Long userId) {

        List<NewsEntity> newsEntityList = newsRepository.findAll();

        Set<Long> favoriteNewsIds;

        //ログインユーザーのお気に入りニュースのnewsIdを取得
        if (userId != null) {
            favoriteNewsIds = new HashSet<>(favoriteNewsRepository.findFavoritreNewsIds(userId));
        } else {
            favoriteNewsIds = Set.of();
        }

        //Entity → Response DTO
        return newsEntityList.stream()
                .map(news -> {
                    NewsBusinessDto businessDto = newsMapper.toBusinessDto(news);
                    NewsResponseDto responseDto = newsMapper.toResponseDto(businessDto);
                    responseDto.setFavorite(
                            userId != null && favoriteNewsIds.contains(news.getId())
                    );
                    return responseDto;
                })
                .toList();

    }

}
