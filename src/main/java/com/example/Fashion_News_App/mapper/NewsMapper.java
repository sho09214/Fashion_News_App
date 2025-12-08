package com.example.Fashion_News_App.mapper;

import com.example.Fashion_News_App.dto.business.NewsBusinessDto;
import com.example.Fashion_News_App.dto.web.NewsResponseDto;
import com.example.Fashion_News_App.entity.NewsEntity;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class NewsMapper {

    public NewsBusinessDto toBusinessDto(NewsEntity entity) {
        NewsBusinessDto dto = new NewsBusinessDto();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());
        dto.setUrl(entity.getUrl());
        dto.setUrlToImage(entity.getUrlToImage());
        dto.setSourceName(entity.getSourceName());
        dto.setPublishedAt(entity.getPublishedAt());
        dto.setCategory(entity.getCategory());

        //紐づくMytagを生成
        List<String> tagNames = entity.getNewsMytagMappingEntitys().stream()
                .map(mapping -> mapping.getMytag().getTagName())
                .collect(Collectors.toList());
        dto.setMyTags(tagNames);

        return dto;
    }

    public NewsResponseDto toResponseDto(NewsBusinessDto businessDto) {
        NewsResponseDto newsResponseDto = new NewsResponseDto();
        newsResponseDto.setId(businessDto.getId());
        newsResponseDto.setTitle(businessDto.getTitle());
        newsResponseDto.setDescription(businessDto.getDescription());
        newsResponseDto.setUrl(businessDto.getUrl());
        newsResponseDto.setImageUrl(businessDto.getUrlToImage());
        newsResponseDto.setSourceName(businessDto.getSourceName());
        newsResponseDto.setCategory(businessDto.getCategory());

        //Mytagを設定
        newsResponseDto.setMyTags(businessDto.getMyTags());

        //to日付フォーマット変換
        if (businessDto.getPublishedAt() != null) {
            String formattedPublishedAt = businessDto.getPublishedAt()
                    .format(DateTimeFormatter.ofPattern("yyyy年MM月dd日"));
            newsResponseDto.setPublishedAt(formattedPublishedAt);
        }

        return newsResponseDto;
    }
}
