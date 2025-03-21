package com.example.schedule.dto.request;

import com.example.schedule.validator.IsPassword;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@NoArgsConstructor
public class UserChangePasswordRequest {
    @NotBlank(message = "기존 비밀번호를 입력해주세요.")
    private String oldPassword;

    @NotBlank(message = "새 비밀번호를 입력해주세요.")
    @IsPassword
    private String newPassword;
}
