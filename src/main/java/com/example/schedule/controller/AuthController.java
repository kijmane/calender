package com.example.schedule.controller;

import com.example.schedule.dto.AuthRequest;
import com.example.schedule.dto.AuthResponse;
import com.example.schedule.dto.RegisterRequest;
import com.example.schedule.service.AuthService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) {
        authService.register(request);
        return ResponseEntity.ok("사용자가 성공적으로 등록되었습니다.");
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request, HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
        AuthResponse response = authService.login(request);
        httpRequest.getSession().setAttribute("user", request.getEmail());
        Cookie cookie = new Cookie("JSESSIONID", httpRequest.getSession().getId());
        cookie.setMaxAge(86400);
        cookie.setHttpOnly(true);
        httpResponse.addCookie(cookie);

        return ResponseEntity.ok(response);
    }
}