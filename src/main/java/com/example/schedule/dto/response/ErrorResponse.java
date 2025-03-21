package com.example.schedule.dto.response;

import lombok.*;

@Getter
@AllArgsConstructor(staticName = "of")
public class ErrorResponse {
    private String message;
    private int status;
}
