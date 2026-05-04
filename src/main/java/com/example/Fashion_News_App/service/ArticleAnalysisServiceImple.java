package com.example.Fashion_News_App.service;

import com.atilika.kuromoji.ipadic.Tokenizer;
import com.atilika.kuromoji.ipadic.Token;
import com.example.Fashion_News_App.entity.NewsEntity;
import com.example.Fashion_News_App.entity.NewsKeywordEntity;
import com.example.Fashion_News_App.entity.NewsRawEntity;
import com.example.Fashion_News_App.repository.NewsKeywordRepository;
import com.example.Fashion_News_App.repository.NewsRawRepository;
import com.example.Fashion_News_App.repository.NewsRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

@Slf4j
@Service
@RequiredArgsConstructor
public class ArticleAnalysisServiceImple implements ArticleAnalysisServiceIF {

    private final NewsRawRepository newsRawRepository;
    private final NewsRepository newsRepository;
    private final NewsKeywordRepository newsKeywordRepository;

    private final Tokenizer tokenizer = new Tokenizer();
    private static final Pattern HTML_PATTERN = Pattern.compile("<[^>]*>");

    // 除外する単語（必要に応じて追加）
    private static final Set<String> STOP_WORDS = Set.of(
            "これ", "それ", "あれ", "この", "その", "あの", "こと", "もの",
            "よう", "そう", "また", "そして", "さらに", "コンテンツ", "配信"
    );

    /**
     * DBから ai_status = 'PENDING' の記事を取得 → キーワード抽出 → DBに登録
     */
    @Transactional
    public void analyzeAllPendingArticles() {
        // 1. DBから取得（ai_status = 'PENDING'）
        List<NewsRawEntity> pendingList = newsRawRepository.findByAiStatus("PENDING");

        log.info("=== 分析開始 ===");
        log.info("処理対象の記事数: {}", pendingList.size());

        int success = 0;
        int failed = 0;

        for (NewsRawEntity raw : pendingList) {
            try {
                // 2. キーワード抽出 → newsテーブル + news_keywordsテーブルに保存
                analyzeSingleArticle(raw);
                success++;
            } catch (Exception e) {
                log.error("分析失敗 id={}: {}", raw.getId(), e.getMessage());
                raw.setAiStatus("FAILED");
                newsRawRepository.save(raw);
                failed++;
            }
        }

        log.info("=== 分析完了: 成功={}, 失敗={} ===", success, failed);
    }

    /**
     * 1件の記事を分析して news と news_keywords に保存
     */
    private void analyzeSingleArticle(NewsRawEntity raw) {
        log.info("処理中: id={}, title={}", raw.getId(), raw.getTitle());

        // キーワード抽出（名詞を抽出）
        List<String> keywords = extractKeywords(raw.getTitle(), raw.getDescription());

        // 要約を生成
        String summary = generateSummary(raw.getDescription());

        // newsテーブルに保存
        NewsEntity news = new NewsEntity();
        news.setId(raw.getId());
        news.setTitle(raw.getTitle());
        news.setDescription(cleanHtml(raw.getDescription()));
        news.setUrl(raw.getUrl());
        news.setPublishedAt(raw.getPublishedAt());
        news.setSummary(summary);
        news.setHash(raw.getHash());
        news.setCreatedAt(LocalDateTime.now());
        news.setUpdatedAt(LocalDateTime.now());

        NewsEntity savedNews = newsRepository.save(news);

        // news_keywordsテーブルに保存
        for (String keyword : keywords) {
            NewsKeywordEntity ke = new NewsKeywordEntity();
            ke.setNewsId(savedNews.getId());
            ke.setKeyword(keyword);
            ke.setKeywordType("noun");
            newsKeywordRepository.save(ke);
        }

        // news_rawのステータスを更新
        raw.setAiStatus("COMPLETED");
        newsRawRepository.save(raw);

        log.info("  完了: news_id={}, keywords={}", savedNews.getId(), keywords);
    }

    /**
     * キーワード抽出（名詞を抽出するだけのシンプルな処理）
     */
    private List<String> extractKeywords(String title, String description) {
        String text = cleanHtml(title) + " " + cleanHtml(description);
        if (text.isBlank()) {
            return List.of();
        }

        List<Token> tokens = tokenizer.tokenize(text);
        Set<String> keywords = new LinkedHashSet<>();

        for (Token token : tokens) {
            String surface = token.getSurface();
            String pos = token.getPartOfSpeechLevel1();

            if (pos.startsWith("名詞")
                    && surface.length() >= 2
                    && !STOP_WORDS.contains(surface)
                    && !surface.matches("\\d+")) {
                keywords.add(surface);
            }
        }

        return keywords.stream().limit(10).toList();
    }

    private String generateSummary(String description) {
        if (description == null) return "";
        String clean = cleanHtml(description);
        if (clean.length() <= 200) return clean;
        return clean.substring(0, 200) + "...";
    }

    private String cleanHtml(String html) {
        if (html == null) return "";
        String cleaned = HTML_PATTERN.matcher(html).replaceAll(" ");
        cleaned = cleaned.replaceAll("\\s+", " ");
        return cleaned.trim();
    }
}
