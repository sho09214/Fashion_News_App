package com.example.Fashion_News_App.config;

import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@Data
@Component
@ConfigurationProperties(prefix = "rss")
public class RssSourceConfig {

    private Map<String, String> sources;
    private Fetch fetch = new Fetch();

    @Data
    public static class Fetch {
        private long delayMs;
    }
}
