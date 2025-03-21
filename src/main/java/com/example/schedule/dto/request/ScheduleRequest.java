package com.example.schedule.dto.request;
import com.example.schedule.entity.User;
import lombok.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.*;

@Getter
@NoArgsConstructor
public class ScheduleRequest {
    @NotBlank(message = "일정 제목은 필수입니다.")
    @Size(max = 10, message = "일정 제목은 10자 이내로 입력해주세요.")
    private String title;

    private String content;

    private User user;

    private Long categoryId;

    private List<Long> tagIds;
}