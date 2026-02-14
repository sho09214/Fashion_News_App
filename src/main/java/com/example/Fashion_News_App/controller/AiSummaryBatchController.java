package com.example.Fashion_News_App.controller;

import com.example.Fashion_News_App.service.AiSummaryBatchServiceIF;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AiSummaryBatchController {

    private final AiSummaryBatchServiceIF aiSummaryBatchServiceIF;

    @PostMapping("/ai-summary")
    public String execute() {
        aiSummaryBatchServiceIF.execute();

        return "AI Summary Done";
    }
}
