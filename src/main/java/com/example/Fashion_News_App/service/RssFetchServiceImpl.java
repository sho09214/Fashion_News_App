package com.example.Fashion_News_App.service;

import com.example.Fashion_News_App.dto.RssFetchResultDto;
import com.example.Fashion_News_App.entity.NewsRawEntity;
import com.example.Fashion_News_App.repository.NewsRawRepository;
import com.example.Fashion_News_App.service.parser.RssParser;
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
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class RssFetchServiceImpl implements RssFetchServiceIF {

    private final NewsRawRepository newsRawRepository;
    private final List<RssParser> parsers;

    @Override
    @Transactional
    public RssFetchResultDto fetch(String sourceKey, String rssUrl) throws Exception {

        log.info("Start fetching RSS: {}", rssUrl);

        RssParser parser = parsers.stream()
                        .filter(p -> p.getSourceName().equals(sourceKey))
                                .findFirst()
                                        .orElseThrow(() -> new RuntimeException("Parser not found:" + sourceKey));

        URL feedUrl = new URL(rssUrl);
        SyndFeedInput input = new SyndFeedInput();
        SyndFeed feed = input.build(new XmlReader(feedUrl));

        List<NewsRawEntity> parsed = parser.parse(feed);

        int fetched = parsed.size();
        int saved = 0;
        int duplicate = 0;

        for (NewsRawEntity e : parsed) {
            if (newsRawRepository.existsByHash(e.getHash())) {
                duplicate++;
                continue;
            }
            newsRawRepository.save(e);
            saved++;
        }
        return new RssFetchResultDto(fetched, saved, duplicate);
    }

}
