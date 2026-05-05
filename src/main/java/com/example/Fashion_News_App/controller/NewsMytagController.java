package com.example.Fashion_News_App.controller;

import com.example.Fashion_News_App.service.NewsMytagServiceIF;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/news/mytag")
@RequiredArgsConstructor
public class NewsMytagController {

    private final NewsMytagServiceIF newsMytagServiceIF;

    //ニュースにマイタグを追加
    @PostMapping("/add/{newsId}")
    public ResponseEntity<Void> addTagToNews(
            Authentication authentication,
            @PathVariable Long newsId,
            @RequestParam String tagName) {

        Long userId = (Long) authentication.getPrincipal();
        newsMytagServiceIF.addTagToNews(userId, newsId, tagName);
        return ResponseEntity.noContent().build();
    }

    //ニュースからマイタグを削除
    @DeleteMapping("/remove/{newsId}")
    public ResponseEntity<Void> removeTagFromNews(
            Authentication authentication,
            @PathVariable Long newsId,
            @RequestParam String tagName) {

        Long userId = (Long) authentication.getPrincipal();
        newsMytagServiceIF.removeTagFromNews(userId, newsId, tagName);
        return ResponseEntity.noContent().build();
    }
}