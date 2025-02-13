package com.example.schedule.controller;

import com.example.schedule.domain.Comment;
import com.example.schedule.dto.CommentRequest;
import com.example.schedule.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/schedules/{scheduleId}/comments")
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    // 댓글 생성 API
    @PostMapping
    public ResponseEntity<String> createComment(@RequestBody CommentRequest request, @PathVariable Long scheduleId) {
        commentService.createComment(request, scheduleId);
        return ResponseEntity.ok("댓글이 성공적으로 생성되었습니다.");
    }

    // 댓글 조회 API
    @GetMapping
    public ResponseEntity<List<Comment>> getComments(@PathVariable Long scheduleId) {
        List<Comment> comments = commentService.getComments(scheduleId);
        return ResponseEntity.ok(comments);
    }

    // 댓글 수정 API
    @PutMapping("/{commentId}")
    public ResponseEntity<String> updateComment(@PathVariable Long scheduleId, @PathVariable Long commentId, @RequestBody CommentRequest request) {
        commentService.updateComment(commentId, scheduleId, request);
        return ResponseEntity.ok("댓글이 성공적으로 수정되었습니다.");
    }

    // 댓글 삭제 API
    @DeleteMapping("/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable Long scheduleId, @PathVariable Long commentId) {
        commentService.deleteComment(commentId, scheduleId);
        return ResponseEntity.ok("댓글이 성공적으로 삭제되었습니다.");
    }
}