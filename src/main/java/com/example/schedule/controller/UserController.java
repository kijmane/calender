package com.example.schedule.controller;

import com.example.schedule.dto.request.UserRequest;
import com.example.schedule.dto.response.UserResponse;
import com.example.schedule.dto.request.RegisterRequest;
import com.example.schedule.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/auth")
public class UserController {

    private final UserService authService;

    public UserController(UserService authService) {
        this.authService = authService;
    }
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) {
        authService.register(request);
        return ResponseEntity.ok("사용자가 성공적으로 등록되었습니다.");
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponse> login(@RequestBody UserRequest request, HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
        UserResponse response = authService.login(request);
        httpRequest.getSession().setAttribute("user", request.getEmail());
        Cookie cookie = new Cookie("JSESSIONID", httpRequest.getSession().getId());
        cookie.setMaxAge(86400);
        cookie.setHttpOnly(true);
        httpResponse.addCookie(cookie);

        return ResponseEntity.ok(response);
    }
}