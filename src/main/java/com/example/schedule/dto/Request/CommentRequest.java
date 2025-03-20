package com.example.schedule.dto.Request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentRequest {
    private String content;
    private Long userId;

    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public CommentRequest(String content, Long userId) {
        this.content = content;
    }
    public CommentRequest() {
        super();
    }
    public CommentRequest(String content) {
        this.content = content;
    }
    public CommentRequest(Long userId, String content) {
        this.userId = userId;
    }
    public CommentRequest(Long userId) {
        this.userId = userId;
    }
}