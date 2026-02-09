package com.example.Fashion_News_App.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RssFetchResultDto {

    private int fetchCount;
    private int savedCount;
    private int duplicateCount;
}
