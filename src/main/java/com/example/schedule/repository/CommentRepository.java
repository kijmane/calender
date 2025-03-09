package com.example.schedule.repository;

import com.example.schedule.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByScheduleId(Long scheduleId);
    Optional<Comment> findByIdAndScheduleId(Long id, Long scheduleId);
}