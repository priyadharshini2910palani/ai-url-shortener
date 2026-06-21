package com.priya.ai_url_shortener.controller;

import com.priya.ai_url_shortener.dto.StatsResponse;
import com.priya.ai_url_shortener.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/stats")
    public StatsResponse getStats() {
        return adminService.getStats();
    }
}