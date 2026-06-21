package com.priya.ai_url_shortener.repository;

import com.priya.ai_url_shortener.model.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import com.priya.ai_url_shortener.model.User;
import java.util.List;
import java.util.Optional;

public interface UrlRepository extends JpaRepository<Url, Long> {

    Optional<Url> findByShortCode(String shortCode);
    List<Url> findByUser(User user);
}