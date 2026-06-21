package com.priya.ai_url_shortener.dto;

public class UrlResponse {

    private String shortUrl;

    public UrlResponse(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public String getShortUrl() {
        return shortUrl;
    }
}