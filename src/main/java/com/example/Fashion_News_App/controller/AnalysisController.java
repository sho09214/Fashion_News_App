package com.example.Fashion_News_App.controller;

import com.example.Fashion_News_App.service.ArticleAnalysisServiceIF;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/admin/analysis")
@RequiredArgsConstructor
public class AnalysisController {

    private final ArticleAnalysisServiceIF analysisServiceIF;

    @PostMapping("/run")
    public Map<String, Object> analyzeAll() {
        long startTime = System.currentTimeMillis();

        analysisServiceIF.analyzeAllPendingArticles();

        long endTime = System.currentTimeMillis();

        Map<String, Object> result = new HashMap<>();
        result.put("status", "completed");
        result.put("elapsedMs", endTime - startTime);
        result.put("message", "分析処理が完了しました");

        return result;
    }
}
