package com.example.Fashion_News_App.mapper;

import com.example.Fashion_News_App.dto.business.MytagBusinessDto;
import com.example.Fashion_News_App.dto.web.MytagResponseDto;
import com.example.Fashion_News_App.entity.MytagEntity;
import org.springframework.stereotype.Component;

@Component
public class MytagMapper {

    public MytagBusinessDto toBusinessDto(MytagEntity entity) {
        MytagBusinessDto dto = new MytagBusinessDto();
        dto.setId(entity.getId());
        dto.setUserId(entity.getUserId());
        dto.setTagName(entity.getTagName());
        dto.setColor(entity.getColor());
        dto.setDisplayOrder(entity.getDisplayOrder());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setUpdatedAt(entity.getUpdatedAt());

        return dto;
    }

    public MytagResponseDto toResponseDto(MytagBusinessDto businessDto) {
        MytagResponseDto responseDto = new MytagResponseDto();
        responseDto.setId(businessDto.getId());
        responseDto.setUserId(businessDto.getUserId());
        responseDto.setTagName(businessDto.getTagName());
        responseDto.setColor(businessDto.getColor());
        responseDto.setDisplayOrder(businessDto.getDisplayOrder());
        responseDto.setCreatedAt(businessDto.getCreatedAt());
        responseDto.setUpdatedAt(businessDto.getUpdatedAt());

        return responseDto;
    }



}
