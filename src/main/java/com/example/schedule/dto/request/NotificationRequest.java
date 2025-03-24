package com.example.schedule.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotificationRequest {
    private String receiver;
    private String content;
}
