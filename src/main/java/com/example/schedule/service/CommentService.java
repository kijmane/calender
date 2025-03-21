package com.example.schedule.service;

import com.example.schedule.entity.Comment;
import com.example.schedule.entity.Schedule;
import com.example.schedule.entity.User;
import com.example.schedule.dto.request.CommentRequest;
import com.example.schedule.repository.CommentRepository;
import com.example.schedule.repository.ScheduleRepository;
import com.example.schedule.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Transactional
@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    public CommentService(CommentRepository commentRepository, ScheduleRepository scheduleRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.scheduleRepository = scheduleRepository;
        this.userRepository = userRepository;
    }

    public void createComment(CommentRequest request, Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new RuntimeException("일정을 찾을 수 없습니다."));
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("유저를 찾을 수 없습니다."));

        Comment comment = Comment.builder()
                .content(request.getContent())
                .user(user)
                .schedule(schedule)
                .build();

        commentRepository.save(comment);
    }

    public List<Comment> getComments(Long scheduleId) {
        return commentRepository.findByScheduleId(scheduleId);
    }

    public void updateComment(Long commentId, Long scheduleId, CommentRequest request) {
        Comment comment = commentRepository.findByIdAndScheduleId(commentId, scheduleId)
                .orElseThrow(() -> new RuntimeException("댓글을 찾을 수 없습니다."));

        comment.updateContent(request.getContent());
    }

    public void deleteComment(Long commentId, Long scheduleId) {
        Comment comment = commentRepository.findByIdAndScheduleId(commentId, scheduleId)
                .orElseThrow(() -> new RuntimeException("댓글을 찾을 수 없습니다."));
        commentRepository.delete(comment);
    }
}