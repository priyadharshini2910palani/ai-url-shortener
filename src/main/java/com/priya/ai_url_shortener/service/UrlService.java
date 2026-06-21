package com.priya.ai_url_shortener.service;

import java.util.List;
import com.priya.ai_url_shortener.dto.UrlRequest;
import com.priya.ai_url_shortener.dto.UrlResponse;
import com.priya.ai_url_shortener.model.Url;
import com.priya.ai_url_shortener.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.priya.ai_url_shortener.model.User;
import com.priya.ai_url_shortener.repository.UserRepository;
import java.time.LocalDate;
import java.util.UUID;
import com.priya.ai_url_shortener.dto.MyUrlResponse;
import java.util.stream.Collectors;

@Service
public class UrlService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;
    @Autowired
    private UrlRepository urlRepository;

    public UrlResponse shortenUrl(
            UrlRequest request,
            String authHeader){

        String shortCode;

        if (request.getCustomCode() != null &&
                !request.getCustomCode().isBlank()) {

            shortCode = request.getCustomCode();

        } else {

            shortCode = UUID.randomUUID()
                    .toString()
                    .substring(0, 6);
        }

        Url url = new Url();
        url.setOriginalUrl(request.getOriginalUrl());
        url.setShortCode(shortCode);

        int days = request.getExpiryDays() == null
                ? 30
                : request.getExpiryDays();

        url.setExpiryDate(LocalDate.now().plusDays(days));
        String token = authHeader.replace("Bearer ", "");

        String email = jwtService.extractEmail(token);

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        url.setUser(user);

        urlRepository.save(url);

        return new UrlResponse(
                "http://localhost:8080/" + shortCode
        );
    }
    public String getOriginalUrl(String shortCode) {

        Url url = urlRepository.findByShortCode(shortCode)
                .orElseThrow(() -> new RuntimeException("URL Not Found"));

        // Check expiry
        if (url.getExpiryDate().isBefore(LocalDate.now())) {
            throw new RuntimeException("URL Expired");
        }

        // Increase click count
        url.setClickCount(url.getClickCount() + 1);

        urlRepository.save(url);

        return url.getOriginalUrl();
    }
    public List<MyUrlResponse> getMyUrls(String authHeader) {

        String token = authHeader.replace("Bearer ", "");

        String email = jwtService.extractEmail(token);

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return urlRepository.findByUser(user)
                .stream()
                .map(url -> new MyUrlResponse(
                        url.getOriginalUrl(),
                        url.getShortCode(),
                        url.getClickCount(),
                        url.getExpiryDate()
                ))
                .collect(Collectors.toList());
    }
}