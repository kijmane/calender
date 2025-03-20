package com.example.schedule.service;

import com.example.schedule.domain.Comment;
import com.example.schedule.domain.Schedule;
import com.example.schedule.domain.User;
import com.example.schedule.dto.Request.CommentRequest;
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

        Comment comment = new Comment(request.getContent(), user, schedule);
        commentRepository.save(comment);
    }

    public List<Comment> getComments(Long scheduleId) {
        return commentRepository.findByScheduleId(scheduleId);
    }
    public void updateComment(Long commentId, Long scheduleId, CommentRequest request) {
        Comment comment = commentRepository.findByIdAndScheduleId(commentId, scheduleId)
                .orElseThrow(() -> new RuntimeException("댓글을 찾을 수 없습니다."));
        comment.setContent(request.getContent());
        commentRepository.save(comment);
    }

    public void deleteComment(Long commentId, Long scheduleId) {
        Comment comment = commentRepository.findByIdAndScheduleId(commentId, scheduleId)
                .orElseThrow(() -> new RuntimeException("댓글을 찾을 수 없습니다."));
        commentRepository.delete(comment);
    }
}