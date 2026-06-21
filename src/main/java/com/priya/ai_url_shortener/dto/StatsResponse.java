package com.priya.ai_url_shortener.dto;

public class StatsResponse {

    private long totalUrls;
    private long totalClicks;
    private long activeUrls;
    private long expiredUrls;

    public StatsResponse(long totalUrls,
                         long totalClicks,
                         long activeUrls,
                         long expiredUrls) {

        this.totalUrls = totalUrls;
        this.totalClicks = totalClicks;
        this.activeUrls = activeUrls;
        this.expiredUrls = expiredUrls;
    }

    public long getTotalUrls() {
        return totalUrls;
    }

    public long getTotalClicks() {
        return totalClicks;
    }

    public long getActiveUrls() {
        return activeUrls;
    }

    public long getExpiredUrls() {
        return expiredUrls;
    }
}