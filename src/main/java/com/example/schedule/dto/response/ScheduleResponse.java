package com.example.schedule.dto.response;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class ScheduleResponse {
    private Long id;
    private String title;
    private String content;
    private String categoryName;
    private List<String> tagNames;
    private String createdBy;
    private LocalDateTime createdAt;
}
