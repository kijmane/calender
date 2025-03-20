package com.example.schedule.validator;

import org.springframework.messaging.handler.annotation.Payload;

public @interface IsPassword {
    String message() default "비밀번호는 8자 이상이며 숫자와 대문자를 포함해야 합니다.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
