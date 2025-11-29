package com.example.Fashion_News_App.service;

import com.example.Fashion_News_App.dto.web.MytagResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MytagServiceIF {

    //マイタグ全件取得
    List<MytagResponseDto> getAllMytags();
}
