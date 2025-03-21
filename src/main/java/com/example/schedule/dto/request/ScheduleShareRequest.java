package com.example.schedule.dto.request;

import com.example.schedule.entity.SharePermission;
import lombok.*;

@Getter
@NoArgsConstructor
public class ScheduleShareRequest {
    private Long inviteeUserId;
    private SharePermission permission;
}
