package com.example.Fashion_News_App.controller;

import com.example.Fashion_News_App.config.RssSourceConfig;
import com.example.Fashion_News_App.dto.RssFetchResultDto;
import com.example.Fashion_News_App.service.RssFetchServiceIF;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class RssAdminController {

    private final RssFetchServiceIF rssFetchServiceIF;
    private final RssSourceConfig rssSourceConfig;

    @PostMapping("/fetch-all")
    public Map<String, RssFetchResultDto> fetchAll() throws Exception {

        Map<String, RssFetchResultDto> result = new HashMap<>();

        for (var entry : rssSourceConfig.getSources().entrySet()) {
            result.put(
                        entry.getKey(),
                    rssFetchServiceIF.fetch(entry.getKey(), entry.getValue())
                    );
            Thread.sleep(rssSourceConfig.getFetch().getDelayMs());
        }

        return result;
    }
}
