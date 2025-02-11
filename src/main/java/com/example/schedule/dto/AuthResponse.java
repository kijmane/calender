package com.example.schedule.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AuthResponse {
    private String token; // JWT 토큰을 담는 필드
    // 토큰 입력받아 AuthResponse 객체 생성
    public AuthResponse(String token) {
        this.token = token;
    }
}