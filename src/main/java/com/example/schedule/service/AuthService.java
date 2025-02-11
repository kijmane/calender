package com.example.schedule.service;

import com.example.schedule.config.JwtUtil;
import com.example.schedule.domain.Role;
import com.example.schedule.domain.User;
import com.example.schedule.dto.AuthRequest;
import com.example.schedule.dto.AuthResponse;
import com.example.schedule.dto.RegisterRequest;
import com.example.schedule.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository userRepository; // User 데이터베이스 리포지토리
    private final PasswordEncoder passwordEncoder; // 비밀번호 암호화를 위한 인코더
    private final JwtUtil jwtUtil; // JWT 유틸리티 클래스

    @Autowired
    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }
    // 사용자 등록 메서드
    public void register(RegisterRequest request) {
        // 전달받은 요청을 기반으로 User 객체 생성
        User user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .name(request.getName())
                .role(Role.USER) // 기본 역할 USER로 설정
                .build();
        userRepository.save(user); // 사용자 정보 DB에 저장
    }
    // 로그인 메서드
    public AuthResponse login(AuthRequest request) {
        Optional<User> userOptional = userRepository.findByEmail(request.getEmail());
        // 사용자 없으면 예외 발생
        if (userOptional.isEmpty()) {
            throw new RuntimeException("유효하지 않은 이메일 또는 비밀번호입니다.");
        }
        // 사용자 객체 가져오기
        User user = userOptional.get();
        // 비밀번호 일치하는지 확인
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("유효하지 않은 이메일 또는 비밀번호입니다.");
        }
        // JWT 토큰 생성
        String token = jwtUtil.generateToken(user.getEmail());
        // 생성된 토큰을 응답으로 반환
        return new AuthResponse(token);
    }
}