package com.example.schedule.config;

import com.example.schedule.domain.User;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Getter
public class CustomUserDetails implements UserDetails {
    private final String email;
    private final String password;
    // User 객체를 통해 CustomUserDetails 생성
    public CustomUserDetails(User user) {
        this.email = user.getEmail();
        this.password = user.getPassword();
    }
    // 권한을 반환하는 메서드
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }
    // 비밀번호 반환
    @Override
    public String getPassword() {return password; }
    // 사용자 이름(이메일) 반환
    @Override
    public String getUsername() {
        return email;
    }
    // 계정이 만료되지 않았음을 의미
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    // 계정이 잠기지 않았음을 의미
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    // 자격 증명이 만료되지 않았음을 의미
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    // 계정이 활성화되었음을 의미
    @Override
    public boolean isEnabled() {
        return true;
    }
}