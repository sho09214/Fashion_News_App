package com.example.Fashion_News_App.controller;

import com.example.Fashion_News_App.dto.business.FavoriteNewsBusinessDto;
import com.example.Fashion_News_App.service.FavoriteNewsServiceIF;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/favorite")
@RequiredArgsConstructor
public class FavoriteNewsController {

    private final FavoriteNewsServiceIF favoriteNewsServiceIF;

    //お気に入り登録
    @PostMapping("/add/{newsId}")
    public ResponseEntity<Void> addFavorite(
            @PathVariable Long newsId,
            Authentication authentication
            ) {
        Long userId = (Long) authentication.getPrincipal();
        favoriteNewsServiceIF.addFavorite(userId, newsId);

        return ResponseEntity.ok().build(); //204レスポンス
    }

    //お気に入り登録解除
    @DeleteMapping("/delete/{newsId}")
    public ResponseEntity<Void> deleteFavorite(
            @PathVariable Long newsId,
            Authentication authentication
    ) {
        Long userId = (Long) authentication.getPrincipal();
        favoriteNewsServiceIF.deleteFavorite(userId, newsId);

        return ResponseEntity.ok().build(); //204レスポンス
    }

}
