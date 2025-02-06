package com.example.schedule.repository;

import com.example.schedule.domain.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findAll();
    Optional<Schedule> findById(Long id);
    void deleteById(Long id);
}