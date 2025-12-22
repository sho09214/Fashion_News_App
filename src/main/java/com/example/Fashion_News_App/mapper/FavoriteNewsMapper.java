package com.example.Fashion_News_App.mapper;

import com.example.Fashion_News_App.dto.business.FavoriteNewsBusinessDto;
import com.example.Fashion_News_App.dto.web.FavoriteNewsResponseDto;
import com.example.Fashion_News_App.entity.FavoriteNewsEntity;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;

@Component
public class FavoriteNewsMapper {

    public FavoriteNewsBusinessDto toBusinessDto(FavoriteNewsEntity entity) {
        FavoriteNewsBusinessDto dto = new FavoriteNewsBusinessDto();
        dto.setId(entity.getId());
        dto.setUserId(entity.getUserId());
        dto.setNewsId(entity.getNewsId());
        dto.setCreatedAt(entity.getCreatedAt());
        return dto;
    }

    public FavoriteNewsResponseDto toResponseDto(FavoriteNewsBusinessDto businessDto) {
        FavoriteNewsResponseDto responseDto = new FavoriteNewsResponseDto();
        responseDto.setId(businessDto.getId());
        responseDto.setNewsId(businessDto.getNewsId());
        responseDto.setUserId(businessDto.getUserId());

        if (businessDto.getCreatedAt() != null) {
            String formattedCreatedAt = businessDto.getCreatedAt()
                    .format(DateTimeFormatter.ofPattern("yyyy年MM月dd日"));
            responseDto.setCreatedAt(formattedCreatedAt);
        }

        return responseDto;
    }
}
