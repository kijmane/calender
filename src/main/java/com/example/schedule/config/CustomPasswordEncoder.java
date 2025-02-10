package com.example.schedule.config;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CustomPasswordEncoder {

        private final PasswordEncoder passwordEncoder;
        // 생성자에서 BCryptPasswordEncoder를 초기화
        public CustomPasswordEncoder() {
            this.passwordEncoder = new BCryptPasswordEncoder();
        }
        // 비밀번호를 암호화하는 메서드
        public String encode(String rawPassword) {
            return passwordEncoder.encode(rawPassword);
        }
        // 비밀번호가 일치하는지 확인하는 메서드
        public boolean matches(String rawPassword, String encodedPassword) {
            return passwordEncoder.matches(rawPassword, encodedPassword);
        }
}