package com.example.Fashion_News_App.service;

import com.example.Fashion_News_App.dto.MytagCreateDto;
import com.example.Fashion_News_App.dto.MytagDisplayOrderUpdateDto;
import com.example.Fashion_News_App.dto.MytagUpdateDto;
import com.example.Fashion_News_App.dto.web.MytagListResponseDto;

import java.util.List;

public interface MytagServiceIF {

    //マイタグ一覧取得
    List<MytagListResponseDto> getAllMytags(Long userId);

    //マイタグ一覧変更(並び順)
    void updateDisplayOrder(Long userId, List<MytagDisplayOrderUpdateDto> mytagDisplayOrderUpdateDto);

    //マイタグ追加
    void create(Long userId, MytagCreateDto mytagCreateDto);

    //マイタグ更新
    void update(Long userId, MytagUpdateDto mytagUpdateDto);

    //マイタグ削除
    void delete(Long userId, Long mytagId);

}