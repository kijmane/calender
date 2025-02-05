package com.example.schedule.config;

import com.example.schedule.domain.User;
import com.example.schedule.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    // 사용자 이메일을 통해 UserDetails 객체 반환
    @Override
    public UserDetails loadUserByUsername(String username) {
        // 사용자 이메일로 DB에서 사용자 정보 조회
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));
        // 조회된 사용자 정보를 CustomUserDetails로 반환
        return new CustomUserDetails(user);
    }
}