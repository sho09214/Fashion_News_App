package com.example.Fashion_News_App.service;

import com.example.Fashion_News_App.entity.NewsRawEntity;
import com.example.Fashion_News_App.entity.NewsViewEntity;
import com.example.Fashion_News_App.repository.NewsRawRepository;
import com.example.Fashion_News_App.repository.NewsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AiSummaryBatchServiceImpl implements AiSummaryBatchServiceIF {

    private final NewsRawRepository newsRawRepository;
    private final NewsRepository newsRepository;
    private final SummarizerServiceIF summarizerServiceIF;

    @Override
    @Transactional
    public void execute() {

        List<NewsRawEntity> rawNews = newsRawRepository.findByAiStatus("NEW");
        log.info("AI summary target count={}", rawNews.size());

        for (NewsRawEntity raw : rawNews) {
            try {

                //重複チェック
                if (newsRepository.findByHash(raw.getHash()).isPresent()) {
                    raw.setAiStatus("DONE");
                    continue;
                }

                //AI要約
                String summary = summarizerServiceIF.summarize(raw.getDescription());

                //保存
                NewsViewEntity news = new NewsViewEntity();
                news.setSourceName(raw.getSourceName());
                news.setTitle(raw.getTitle());
                news.setDescription(summary);
                news.setUrl(raw.getUrl());
                news.setHash(raw.getHash());
                news.setPublishedAt(raw.getPublishedAt());
                news.setCreatedAt(LocalDateTime.now());
                news.setUpdatedAt(LocalDateTime.now());
                newsRepository.save(news);

                //Ai status変更
                raw.setAiStatus("DONE");

            } catch (Exception e) {
                log.error("AI Summary Error");
                raw.setAiStatus("ERROR");
            }
        }
    }
}
