package com.example.Fashion_News_App.service;

import com.example.Fashion_News_App.entity.MytagEntity;
import com.example.Fashion_News_App.entity.NewsEntity;
import com.example.Fashion_News_App.entity.NewsMytagMappingEntity;
import com.example.Fashion_News_App.repository.MytagRepository;
import com.example.Fashion_News_App.repository.NewsMytagRepository;
import com.example.Fashion_News_App.repository.NewsRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class NewsMytagServiceImpl implements NewsMytagServiceIF {

    private final MytagRepository mytagRepository;
    private final NewsRepository newsRepository;
    private final NewsMytagRepository newsMytagRepository;

    @Override
    @Transactional
    public void addTagToNews(Long userId, Long newsId, String tagName) {
        // 1. タグを取得（なければ例外）
        MytagEntity tag = mytagRepository.findByUserIdAndTagName(userId, tagName)
                .orElseThrow(() -> new RuntimeException("マイタグが見つかりません: " + tagName));

        // 2. ニュースを取得
        NewsEntity news = newsRepository.findById(newsId)
                .orElseThrow(() -> new RuntimeException("ニュースが見つかりません: " + newsId));

        // 3. 既にマッピングがあれば何もしない
        if (newsMytagRepository.existsByNewsEntityIdAndMyTagEntityIdAndUserId(newsId, tag.getId(), userId)) {
            log.info("既にマッピング済み: newsId={}, tagName={}", newsId, tagName);
            return;
        }

        // 4. マッピング作成
        NewsMytagMappingEntity mapping = new NewsMytagMappingEntity();
        mapping.setNewsEntity(news);
        mapping.setMyTagEntity(tag);
        mapping.setUserId(userId);
        newsMytagRepository.save(mapping);

        log.info("マイタグ追加: userId={}, newsId={}, tagName={}", userId, newsId, tagName);
    }

    @Override
    @Transactional
    public void removeTagFromNews(Long userId, Long newsId, String tagName) {
        // 1. タグを取得
        MytagEntity tag = mytagRepository.findByUserIdAndTagName(userId, tagName)
                .orElseThrow(() -> new RuntimeException("マイタグが見つかりません: " + tagName));

        // 2. マッピングを削除
        newsMytagRepository.deleteByNewsEntityIdAndMyTagEntityIdAndUserId(newsId, tag.getId(), userId);

        log.info("マイタグ削除: userId={}, newsId={}, tagName={}", userId, newsId, tagName);
    }
}
