package com.example.Fashion_News_App.controller;

import com.example.Fashion_News_App.dto.MytagCreateDto;
import com.example.Fashion_News_App.dto.MytagUpdateDto;
import com.example.Fashion_News_App.dto.business.MytagBusinessDto;
import com.example.Fashion_News_App.dto.web.MytagResponseDto;
import com.example.Fashion_News_App.service.MytagServiceIF;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/mytags")
@RequiredArgsConstructor
public class MytagController {

    private final MytagServiceIF mytagServiceIF;

    //マイタグ一覧取得

    //マイタグ一覧変更(並び順)

    //マイタグ追加
    @PostMapping("/add")
    public ResponseEntity<Void> create(
            @RequestBody MytagCreateDto request,
            Authentication authentication
            ) {
        Long userId = (Long) authentication.getPrincipal();
        mytagServiceIF.create(userId, request);
        return ResponseEntity.noContent().build(); //204レスポンス
    }

    //マイタグ更新
    @PatchMapping("/{mytagId}")
    public ResponseEntity<Void> update(
            @RequestBody MytagUpdateDto mytagUpdateDto,
            Authentication authentication
    ) {
        Long userId = (Long) authentication.getPrincipal();
        mytagServiceIF.update(userId, mytagUpdateDto);
        return ResponseEntity.noContent().build(); //204レスポンス
    }

    //マイタグ削除
    @DeleteMapping("/delete/{mytagId}")
    public ResponseEntity<Void> delete(
            @PathVariable Long mytagId,
            Authentication authentication
    ) {
        Long userId = (Long) authentication.getPrincipal();
        mytagServiceIF.delete(userId, mytagId);

        return ResponseEntity.noContent().build(); //204レスポンス
    }
}
