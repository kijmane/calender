package com.example.schedule.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommentRequest {

    @NotBlank(message = "댓글 내용을 입력해주세요.")
    private String content;

    @NotNull(message = "작성자 ID는 필수입니다.")
    private Long userId;
}