package com.example.schedule.service;

import com.example.schedule.domain.Comment;
import com.example.schedule.domain.Schedule;
import com.example.schedule.domain.User;
import com.example.schedule.dto.CommentRequest;
import com.example.schedule.repository.CommentRepository;
import com.example.schedule.repository.ScheduleRepository;
import com.example.schedule.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
@Transactional // 트랜잭션 관리 : 메서드 DB에 대한 변경을 포함하므로 트랜잭션 내에서 실행
@Service // 이 클래스가 서비스 레이어임을 나타냄
public class CommentService {

    private final CommentRepository commentRepository;
    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;
    // 생성자 주입 : 의존성 주입 방식으로 각 리포지토리를 주입받음
    public CommentService(CommentRepository commentRepository, ScheduleRepository scheduleRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.scheduleRepository = scheduleRepository;
        this.userRepository = userRepository;
    }
    // 댓글 생성 메서드
    public void createComment(CommentRequest request, Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new RuntimeException("일정을 찾을 수 없습니다."));
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("유저를 찾을 수 없습니다."));

        Comment comment = new Comment(request.getContent(), user, schedule);
        commentRepository.save(comment);
    }
    // 특정 일정에 달린 댓글 조회 메서드
    public List<Comment> getComments(Long scheduleId) {
        return commentRepository.findByScheduleId(scheduleId);
    }
    // 댓글 수정 메서드
    public void updateComment(Long commentId, Long scheduleId, CommentRequest request) {
        Comment comment = commentRepository.findByIdAndScheduleId(commentId, scheduleId)
                .orElseThrow(() -> new RuntimeException("댓글을 찾을 수 없습니다."));
        comment.setContent(request.getContent());
        commentRepository.save(comment);
    }
    // 댓글 삭제 메서드
    public void deleteComment(Long commentId, Long scheduleId) {
        Comment comment = commentRepository.findByIdAndScheduleId(commentId, scheduleId)
                .orElseThrow(() -> new RuntimeException("댓글을 찾을 수 없습니다."));
        commentRepository.delete(comment);
    }
}