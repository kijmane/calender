package com.example.schedule.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.config.http.SessionCreationPolicy;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {

    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;
    // 생성자 주입으로 JwtUtil과 UserDetailsService를 받아옴
    public SecurityConfig(JwtUtil jwtUtil, UserDetailsService userDetailsService) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }
    // BCryptPasswordEncoder를 사용하여 비밀번호를 암호화하는 PasswordEncoder 빈을 설정
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    // JwtFilter를 빈으로 등록하여 JWT 인증 필터를 설정
    @Bean
    public JwtFilter jwtFilter() {
        return new JwtFilter(jwtUtil, userDetailsService);
    }
    // Spring Security의 HTTP 보안 설정 / 로그인 , 로그아웃 , 세션 관리 등을 설정
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable()) // CSRF 보호 비활성화
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/api/auth/register", "/api/auth/login").permitAll() // 회원가입과 로그인 API는 모두에게 허용
                                .anyRequest().authenticated() // 그 외 모든 요청은 인증된 사용자만 접근 가능
                )
                .formLogin(withDefaults()) // 기본 로그인 설정
                .logout(withDefaults()) // 기본 로그아웃 설정
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED) // 필요할 때만 세션을 생성
                );
        return http.build();
    }
}