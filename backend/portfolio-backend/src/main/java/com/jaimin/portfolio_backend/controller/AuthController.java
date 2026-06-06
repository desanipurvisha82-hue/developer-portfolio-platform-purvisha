package com.jaimin.portfolio_backend.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jaimin.portfolio_backend.dto.AuthResponse;
import com.jaimin.portfolio_backend.dto.LoginRequest;
import com.jaimin.portfolio_backend.dto.RegisterRequest;
import com.jaimin.portfolio_backend.service.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public AuthResponse register(
            @RequestBody RegisterRequest request) {

        String message = authService.register(request);

        return new AuthResponse(message);
    }

    @PostMapping("/login")
    public AuthResponse login(
            @RequestBody LoginRequest request) {

        String token = authService.login(request);

        return new AuthResponse(token);
    }
}