package com.example.Fashion_News_App.controller;

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

    //マイタグ追加
    @PostMapping("/add")
    public ResponseEntity<Void> create(
            @RequestBody MytagBusinessDto request,
            Authentication authentication
            ) {
        Long userId = (Long) authentication.getPrincipal();
        mytagServiceIF.create(userId, request);
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
