package com.example.schedule.config;

import com.example.schedule.domain.User;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

// 사용자 정보를 담고 있는 CustomUserDetails 클래스
// UserDetails 인터페이스를 구현하여 Spring Security에서 인증/권한 처리를 담당합니다
@Getter
public class CustomUserDetails implements UserDetails {
    private final String email; // 사용자 이메일
    private final String password; // 사용자 암호화된 비밀번호

    public CustomUserDetails(User user) {
        this.email = user.getEmail(); // 이메일 설정
        this.password = user.getPassword(); // 비밀번호 설정
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // 현재는 권한을 처리하지 않으므로 null 반환
        return null;
    }

    @Override
    public String getPassword() {return password; }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}