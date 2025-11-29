package com.example.Fashion_News_App.controller;

import com.example.Fashion_News_App.dto.web.MytagResponseDto;
import com.example.Fashion_News_App.dto.web.NewsResponseDto;
import com.example.Fashion_News_App.service.MytagServiceIF;
import com.example.Fashion_News_App.service.NewsServiceIF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class TopController {

    @Autowired
    private NewsServiceIF newsServiceIF;

    @Autowired
    private MytagServiceIF mytagServiceIF;

    @GetMapping("/")
    public String top(Model model) {
        //ニュース全件取得
        List<NewsResponseDto> newsResponseDtoList = newsServiceIF.getAllNews();

        //マイタグ取得
        List<MytagResponseDto> mytagResponseDtoList = mytagServiceIF.getAllMytags();

        //ニュース、マイタグ有無チェック
        if (newsResponseDtoList != null) {
            model.addAttribute("newsList", newsResponseDtoList);
        }
        if (mytagResponseDtoList != null) {
            model.addAttribute("mytagList", mytagResponseDtoList);
        }
        return "index";
    }

    //ログイン/新規会員登録画面遷移
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    //マイページ画面遷移
    @GetMapping("/mypage")
    public String mypage() {
        return "login";
    }

    //マイタグ画面遷移
    @GetMapping("/mytag")
    public String mytag() {
        return "mytag";
    }

}
