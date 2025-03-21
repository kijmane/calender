package com.example.schedule.dto.response;

import com.example.schedule.entity.SharePermission;
import com.example.schedule.entity.ShareStatus;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public class ScheduleShareResponse {
    private Long shareId;
    private Long scheduleId;
    private String scheduleTitle;
    private String inviteeEmail;
    private SharePermission permission;
    private ShareStatus status;
    private LocalDateTime invitedAt;
}