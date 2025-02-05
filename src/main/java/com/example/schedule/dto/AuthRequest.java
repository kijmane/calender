package com.example.schedule.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthRequest {
    private String email;
    private String password;

    // 추가된 getter 메서드
    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }
}