package com.example.Fashion_News_App.controller;

import com.example.Fashion_News_App.dto.business.FavoriteNewsBusinessDto;
import com.example.Fashion_News_App.service.FavoriteNewsServiceIF;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/favorite")
@RequiredArgsConstructor
public class FavoriteNewsController {

    private final FavoriteNewsServiceIF favoriteNewsServiceIF;

    //お気に入り登録
    @PostMapping("/add")
    public ResponseEntity<Void> addFavorite(
            @RequestBody FavoriteNewsBusinessDto favoriteNewsBusinessDto,
            Authentication authentication
            ) {
        Long userId = (Long) authentication.getPrincipal();
        favoriteNewsServiceIF.addFavorite(userId, favoriteNewsBusinessDto.getNewsId());

        return ResponseEntity.ok().build(); //204レスポンス
    }

}
