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
    // 생성자 주입으로 AuthService를 받아옴
    public AuthController(AuthService authService) {
        this.authService = authService;
    }
    // 회원가입 API
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) {
        authService.register(request); // AuthService를 통해 사용자 등록
        return ResponseEntity.ok("사용자가 성공적으로 등록되었습니다."); // 사용자 등록 완료 후 성공 메시지 반환
    }
    // 로그인 API
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request, HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
        AuthResponse response = authService.login(request); // AuthService를 통해 로그인 처리
        // 로그인 성공 시 세션에 사용자 정보를 저장
        httpRequest.getSession().setAttribute("user", request.getEmail());
        // 로그인 세션에 쿠키 추가
        Cookie cookie = new Cookie("JSESSIONID", httpRequest.getSession().getId());
        cookie.setMaxAge(86400); // 쿠키 유효 시간 설정 (24시간)
        cookie.setHttpOnly(true); // JavaScript에서 쿠키에 접근할 수 없도록 설정
        httpResponse.addCookie(cookie); // 쿠키를 HTTP 응답에 추가

        return ResponseEntity.ok(response); // 로그인 성공 후 JWT 토큰을 포함한 응답 반환
    }
}