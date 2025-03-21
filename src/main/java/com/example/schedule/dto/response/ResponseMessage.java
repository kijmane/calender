package com.example.schedule.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ResponseMessage {
    private String message;
    private int status;
    private LocalDateTime timestamp;

    public static ResponseMessage of(String message, int status) {
        return new ResponseMessage(message, status, LocalDateTime.now());
    }
}
