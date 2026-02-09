package com.example.Fashion_News_App.service;

import com.example.Fashion_News_App.dto.RssFetchResultDto;
import com.example.Fashion_News_App.entity.NewsRawEntity;
import com.example.Fashion_News_App.repository.NewsRawRepository;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HexFormat;

@Slf4j
@Service
@RequiredArgsConstructor
public class RssFetchServiceImpl implements RssFetchServiceIF {

    private final NewsRawRepository newsRawRepository;

    @Override
    @Transactional
    public RssFetchResultDto fetchRss(String sourceName, String rssUrl) throws Exception {

        log.info("Start fetching RSS: {}", rssUrl);

        int fetched = 0;
        int saved = 0;
        int duplicate = 0;

        URL feedUrl = new URL(rssUrl);
        SyndFeedInput input = new SyndFeedInput();
        SyndFeed feed = input.build(new XmlReader(feedUrl));

        log.info("Feed title={}, entryes={}", feed.getTitle(), feed.getEntries().size());

        for (SyndEntry entry : feed.getEntries()) {
            fetched++;

            String title = entry.getTitle();
            String url = entry.getLink();
            String content = entry.getDescription() != null ? entry.getDescription().getValue() : "";

            log.debug("Entry title={}, url={}", title, url);

            String hash = sha256(title + "|" + url);

            //重複防止
            if (newsRawRepository.existsByHash(hash)) {
                duplicate++;
                log.debug("Skip duplicate: {}", url);
                continue;
            }

            NewsRawEntity entity = new NewsRawEntity();
            entity.setSourceName(sourceName);
            entity.setTitle(title);
            entity.setRawContent(content);
            entity.setUrl(url);
            entity.setPublishedAt(
                    entry.getPublishedDate() != null ?
                    entry.getPublishedDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()
                            : null
                    );
            entity.setFetchedAt(LocalDateTime.now());
            entity.setLanguage("ja");
            entity.setCountry("JP");
            entity.setHash(hash);

            newsRawRepository.save(entity);
            saved++;
            log.info("Saved news_raw title={}", title);
        }

        log.info("RSS fetch finished. fetched={}, saved={}, duplicate={}", fetched, saved, duplicate);
        return new RssFetchResultDto(fetched, saved, duplicate);
    }

    private String sha256(String url) throws Exception {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(url.getBytes(StandardCharsets.UTF_8));
        return HexFormat.of().formatHex(hash);
    }
}
