package com.example.schedule.repository;

import com.example.schedule.domain.Schedule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    // 모든 일정을 조회하는 메서드
    List<Schedule> findAll();
    // 특정 ID를 가진 일정 조회하는 메서드
    Optional<Schedule> findById(Long id);
    // 특정 ID를 가진 일정 삭제하는 메서드
    void deleteById(Long id);
    // 페이징 기능 추가
    Page<Schedule> findAll(Pageable pageable);
}