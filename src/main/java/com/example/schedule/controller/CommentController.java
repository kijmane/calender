package com.example.schedule.controller;

import com.example.schedule.aop.AdminLogging;
import com.example.schedule.dto.response.ResponseMessage;
import com.example.schedule.entity.Comment;
import com.example.schedule.dto.request.CommentRequest;
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

    @PostMapping
    public ResponseEntity<ResponseMessage> createComment(@RequestBody CommentRequest request, @PathVariable Long scheduleId) {
        commentService.createComment(request, scheduleId);
        return ResponseEntity.ok(ResponseMessage.of("댓글이 성공적으로 생성되었습니다.",200));
    }

    @GetMapping
    public ResponseEntity<List<Comment>> getComments(@PathVariable Long scheduleId) {
        List<Comment> comments = commentService.getComments(scheduleId);
        return ResponseEntity.ok(comments);
    }

    @AdminLogging
    @PutMapping("/{commentId}")
    public ResponseEntity<String> updateComment(@PathVariable Long scheduleId, @PathVariable Long commentId, @RequestBody CommentRequest request) {
        commentService.updateComment(commentId, scheduleId, request);
        return ResponseEntity.ok("댓글이 성공적으로 수정되었습니다.");
    }

    @AdminLogging
    @DeleteMapping("/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable Long scheduleId, @PathVariable Long commentId) {
        commentService.deleteComment(commentId, scheduleId);
        return ResponseEntity.ok("댓글이 성공적으로 삭제되었습니다.");
    }
}