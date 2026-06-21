package com.priya.ai_url_shortener.service;

import com.priya.ai_url_shortener.dto.StatsResponse;
import com.priya.ai_url_shortener.model.Url;
import com.priya.ai_url_shortener.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AdminService {

    @Autowired
    private UrlRepository urlRepository;

    public StatsResponse getStats() {

        List<Url> urls = urlRepository.findAll();

        long totalUrls = urls.size();

        long totalClicks = urls.stream()
                .mapToLong(Url::getClickCount)
                .sum();

        long activeUrls = urls.stream()
                .filter(url ->
                        !url.getExpiryDate().isBefore(LocalDate.now()))
                .count();

        long expiredUrls = urls.stream()
                .filter(url ->
                        url.getExpiryDate().isBefore(LocalDate.now()))
                .count();

        return new StatsResponse(
                totalUrls,
                totalClicks,
                activeUrls,
                expiredUrls
        );
    }
}