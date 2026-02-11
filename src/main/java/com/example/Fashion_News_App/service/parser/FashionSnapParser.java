package com.example.Fashion_News_App.service.parser;

import com.example.Fashion_News_App.entity.NewsRawEntity;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HexFormat;
import java.util.List;

@Slf4j
@Component
public class FashionSnapParser implements RssParser {
    @Override
    public String getSourceName() {
        return "fashionsnap";
    }

    @Override
    public List<NewsRawEntity> parse(SyndFeed feed) throws Exception {
        log.info("FashionSnap Parser Called!!");
        List<NewsRawEntity> list = new ArrayList<>();

        for (SyndEntry entry : feed.getEntries()) {
            NewsRawEntity entity = new NewsRawEntity();

            String description = "";
            if (entry.getDescription() != null) {
                description = entry.getDescription().getValue();
            } else if (!entry.getContents().isEmpty()) {
                // fallback only
                description = entry.getContents().get(0).getValue();
            }

            entity.setSourceName(getSourceName());
            entity.setTitle(entry.getTitle());
            entity.setDescription(description);
            entity.setUrl(entry.getLink());
            entity.setPublishedAt(convert(entry.getPublishedDate()));
            entity.setFetchedAt(LocalDateTime.now());
            entity.setLanguage("JP");
            entity.setCountry("ja");
            entity.setHash(sha256(entry.getLink()));

            list.add(entity);
        }
        return list;
    }


    private LocalDateTime convert(Date publishedDate) {
        if (publishedDate == null) return null;
        return publishedDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    private String sha256(String link) throws Exception {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        return HexFormat.of().formatHex(digest.digest(link.getBytes(StandardCharsets.UTF_8)));
    }
}
