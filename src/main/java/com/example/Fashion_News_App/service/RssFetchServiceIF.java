package com.example.Fashion_News_App.service;

import com.example.Fashion_News_App.dto.RssFetchResultDto;

public interface RssFetchServiceIF {

    RssFetchResultDto fetch(String sourceKey, String rssUrl) throws Exception;
}
