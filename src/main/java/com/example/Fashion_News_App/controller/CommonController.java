package com.example.Fashion_News_App.controller;

import com.example.Fashion_News_App.model.NewsModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CommonController {

    //トップページ繊維
    @GetMapping("/")
    public String top(Model model) {
        List<NewsModel> newsList = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            NewsModel news = new NewsModel();
            news.setId(String.valueOf(i));
            news.setTitle("テスト" + i);
            news.setDescription("テスト説明" + i);
            newsList.add(news);
        }

        model.addAttribute("newsList", newsList);
        return "index";
    }
}
