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
    // 사용자 이메일로 사용자 정보를 조회하여 UserDetails객체를 반환
    @Override
    public UserDetails loadUserByUsername(String username) {
        // 이메일로 사용자 조회 , 없으면 예외 발생
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));
        // 조회된 사용자 정보를 CustomUserDetails로 변환하여 반환
        return new CustomUserDetails(user);
    }
}