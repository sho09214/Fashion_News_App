package com.example.Fashion_News_App;

import com.example.Fashion_News_App.config.RssSourceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class FashionNewsAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(FashionNewsAppApplication.class, args);
	}

}
