package com.priya.ai_url_shortener.service;

import com.priya.ai_url_shortener.dto.RegisterRequest;
import com.priya.ai_url_shortener.model.User;
import com.priya.ai_url_shortener.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.priya.ai_url_shortener.dto.LoginRequest;
import com.priya.ai_url_shortener.service.JwtService;


@Service
public class AuthService {

    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserRepository userRepository;

    public String register(RegisterRequest request) {

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            return "Email already exists";
        }

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setRole("USER");

        userRepository.save(user);

        return "User Registered Successfully";
    }
    public String login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElse(null);

        if (user == null) {
            return "User Not Found";
        }

        if (!user.getPassword().equals(request.getPassword())) {
            return "Invalid Password";
        }

        return jwtService.generateToken(user.getEmail());
    }
}