package com.example.Fashion_News_App.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TopController {

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
