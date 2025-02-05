package com.example.schedule.repository;

import com.example.schedule.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, Long> {  // ID를 Long으로 수정
    Optional<User> findByEmail(String email);
}