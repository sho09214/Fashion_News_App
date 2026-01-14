package com.example.Fashion_News_App.dto.web;

import com.example.Fashion_News_App.entity.MytagEntity;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MytagResponseDto {

    private Long id;
    private Long userId;
    private String tagName;
    private String color;
    private int displayOrder;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static MytagResponseDto from(MytagEntity entity) {
        MytagResponseDto responseDto = new MytagResponseDto();
        responseDto.setId(entity.getId());
        responseDto.setTagName(entity.getTagName());
        responseDto.setColor(entity.getColor());
        responseDto.setDisplayOrder(entity.getDisplayOrder());
        return responseDto;
    }

    public MytagResponseDto() {

    }
}
