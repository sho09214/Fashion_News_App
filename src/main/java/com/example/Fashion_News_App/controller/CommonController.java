package com.example.Fashion_News_App.controller;

import com.example.Fashion_News_App.dto.web.NewsResponseDto;
import com.example.Fashion_News_App.model.NewsModel;
import com.example.Fashion_News_App.service.TopServiceIF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CommonController {

    @Autowired
    private TopServiceIF topServiceIF;

    //トップページ繊維
    @GetMapping("/")
    public String top(Model model) {

        //ニュース全件取得
        List<NewsResponseDto> newsList = topServiceIF.getAllNews();
        if (newsList != null) {
            model.addAttribute("newsList", newsList);
        }
        System.out.println(newsList);
        return "index";
    }
}
