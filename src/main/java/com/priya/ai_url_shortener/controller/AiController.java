package com.priya.ai_url_shortener.controller;

import com.priya.ai_url_shortener.service.GeminiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ai")
public class AiController {

    @Autowired
    private GeminiService geminiService;

    @GetMapping("/analyze")
    public String analyze(@RequestParam String url) {
        return geminiService.analyzeUrl(url);
    }

    @GetMapping("/test")
    public String test() {
        return "AI Controller Working";
    }

    @GetMapping("/key")
    public String key() {
        return "KEY LOADED";
    }
}
