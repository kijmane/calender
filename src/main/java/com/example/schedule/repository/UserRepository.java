package com.example.schedule.repository;

import com.example.schedule.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // 이메일을 통해 사용자 정보 조회하는 메서드
    Optional<User> findByEmail(String email);
}