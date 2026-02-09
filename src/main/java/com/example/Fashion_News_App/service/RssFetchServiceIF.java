package com.example.Fashion_News_App.service;

import com.example.Fashion_News_App.dto.RssFetchResultDto;

public interface RssFetchServiceIF {

    RssFetchResultDto fetchRss(String sourceName, String rssUrl) throws Exception;
}
