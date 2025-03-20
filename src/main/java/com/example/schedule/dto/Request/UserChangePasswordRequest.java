package com.example.schedule.dto.Request;

import com.example.schedule.validator.IsPassword;
import jakarta.validation.constraints.NotBlank;

public class UserChangePasswordRequest {
    @NotBlank
    private String oldPassword;

    @NotBlank
    @IsPassword
    private String newPassword;
}
