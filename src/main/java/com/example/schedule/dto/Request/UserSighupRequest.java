package com.example.schedule.dto.Request;

import com.example.schedule.validator.IsPassword;
import jakarta.validation.constraints.NotBlank;

public class UserSighupRequest {
    @NotBlank
    private String email;

    @NotBlank
    @IsPassword
    private String password;
}
