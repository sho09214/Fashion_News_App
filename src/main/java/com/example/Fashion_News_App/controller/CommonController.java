package com.example.Fashion_News_App.controller;

import com.example.Fashion_News_App.model.NewsModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CommonController {

    //トップページ繊維
    @GetMapping("/")
    public String top(Model model) {
        NewsModel news = new NewsModel();
        news.setId("1");
        news.setTitle("テスト");
        news.setDescription("テスト説明");

        model.addAttribute("newsList", news);
        return "index";
    }
}
