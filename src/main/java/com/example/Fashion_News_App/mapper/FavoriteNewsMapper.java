package com.example.Fashion_News_App.mapper;

import com.example.Fashion_News_App.dto.business.FavoriteNewsBusinessDto;
import com.example.Fashion_News_App.dto.web.FavoriteNewsResponseDto;
import com.example.Fashion_News_App.entity.FavoriteNewsEntiry;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;

@Component
public class FavoriteNewsMapper {

    public FavoriteNewsBusinessDto toBusinessDto(FavoriteNewsEntiry entity) {
        FavoriteNewsBusinessDto dto = new FavoriteNewsBusinessDto();
        dto.setId(entity.getId());
//        dto.setUserId(entity.getUser().getId());
        dto.setNewsId(entity.getNewsEntity().getId());
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
