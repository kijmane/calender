package com.example.schedule.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;
    // 생성자 : JwtUtil과 UserDetailsService를 주입받음
    public JwtFilter(JwtUtil jwtUtil, UserDetailsService userDetailsService) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }
    // 필터 내부 로직 : 요청마다 호출되어 JWT 인증을 처리
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        String requestURI = request.getRequestURI();

        if (requestURI.equals("/api/auth/login") || requestURI.equals("/api/auth/register")) {
            chain.doFilter(request, response);
            return;
        }
        // Authorization 헤더에서 JWT 추출 및 검증
        String token = extractToken(request);
        if (token != null && jwtUtil.validateToken(token)) {
            // 유효한 토큰인 경우 사용자 정보 로드
            String email = jwtUtil.extractEmail(token);
            UserDetails userDetails = userDetailsService.loadUserByUsername(email);
            // 인증 객체를 만들어 SecurityContext에 설정
            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        // 필터 체인 계속 진행
        chain.doFilter(request, response);
    }
    // 요청에서 Authorization 헤더를 추출하여 토큰만 반환하는 메서드
    private String extractToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        // Bearer 토큰 형식인지 확인
        if (header != null && header.startsWith("Bearer ")) {
            return header.substring(7);
        }
        return null;
    }
}