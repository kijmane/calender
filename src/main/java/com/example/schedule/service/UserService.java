package com.example.schedule.service;

import com.example.schedule.config.jwt.JwtUtil;
import com.example.schedule.entity.Role;
import com.example.schedule.entity.User;
import com.example.schedule.dto.request.UserRequest;
import com.example.schedule.dto.response.UserResponse;
import com.example.schedule.dto.request.RegisterRequest;
import com.example.schedule.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public void register(RegisterRequest request) {
        User user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .name(request.getName())
                .role(Role.USER)
                .build();
        userRepository.save(user);
    }

    public UserResponse login(UserRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("유효하지 않은 이메일 또는 비밀번호입니다."));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("유효하지 않은 이메일 또는 비밀번호입니다.");
        }
        String token = jwtUtil.generateToken(user.getEmail());
        return new UserResponse(token);
    }
}