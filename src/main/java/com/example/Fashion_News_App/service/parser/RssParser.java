package com.example.Fashion_News_App.service.parser;

import com.example.Fashion_News_App.entity.NewsRawEntity;
import com.rometools.rome.feed.synd.SyndFeed;

import java.util.List;

public interface RssParser {
    String getSourceName();
    List<NewsRawEntity> parse(SyndFeed feed) throws Exception;
}
