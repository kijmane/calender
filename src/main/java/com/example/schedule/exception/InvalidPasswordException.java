package com.example.schedule.exception;

public class InvalidPasswordException extends RuntimeException {
    // 생성자에서 오류 메시지 전달받아 부모 클래스인 RuntimeException에 전달
    public InvalidPasswordException(String message) {
        super(message);
    }
}