package com.priya.ai_url_shortener.controller;

import com.priya.ai_url_shortener.dto.MyUrlResponse;
import com.priya.ai_url_shortener.dto.UrlRequest;
import com.priya.ai_url_shortener.dto.UrlResponse;
import com.priya.ai_url_shortener.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/url")
public class UrlController {

    @Autowired
    private UrlService urlService;

    @PostMapping("/shorten")
    public UrlResponse shortenUrl(
            @RequestBody UrlRequest request,
            @RequestHeader("Authorization") String authHeader) {

        return urlService.shortenUrl(request, authHeader);
    }

    @GetMapping("/my-urls")
    public List<MyUrlResponse> getMyUrls(
            @RequestHeader("Authorization") String authHeader) {

        return urlService.getMyUrls(authHeader);
    }

    @GetMapping("/{shortCode}")
    public ResponseEntity<Void> redirect(
            @PathVariable String shortCode) {

        String originalUrl =
                urlService.getOriginalUrl(shortCode);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", originalUrl);

        return ResponseEntity.status(302)
                .headers(headers)
                .build();
    }
}