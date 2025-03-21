package com.example.schedule.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Slf4j
@Aspect
@Component
public class AdminLoggingAspect {
    @Around("@annotation(AdminLogging)")
    public Object logAdminAccess(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        String methodName = joinPoint.getSignature().toShortString();
        String requestBody = Arrays.toString(joinPoint.getArgs());

        Object result = joinPoint.proceed();

        long endTime = System.currentTimeMillis();
        log.info("Admin API 호출 로그 - 메서드: {}, 요청 값: {}, 응답 값: {}, 소요 시간: {}ms",
                methodName, requestBody, result, (endTime - startTime));
        return result;
    }
}