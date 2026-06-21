package com.priya.ai_url_shortener.dto;

import java.time.LocalDate;

public class MyUrlResponse {

    private String originalUrl;
    private String shortCode;
    private Long clickCount;
    private LocalDate expiryDate;

    public MyUrlResponse(String originalUrl,
                         String shortCode,
                         Long clickCount,
                         LocalDate expiryDate) {

        this.originalUrl = originalUrl;
        this.shortCode = shortCode;
        this.clickCount = clickCount;
        this.expiryDate = expiryDate;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public String getShortCode() {
        return shortCode;
    }

    public Long getClickCount() {
        return clickCount;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }
}