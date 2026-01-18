package com.example.Fashion_News_App.dto.web;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MytagListResponseDto {

    private Long id;
    private String tagName;
    private int displayOrder;
}
