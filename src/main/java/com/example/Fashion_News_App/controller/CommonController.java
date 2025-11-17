package com.example.Fashion_News_App.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CommonController {

    //トップページ繊維
    @GetMapping("/")
    public String top() {
        return "index";
    }
}
