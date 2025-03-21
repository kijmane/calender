package com.example.schedule.controller;

import com.example.schedule.config.security.CustomUserDetails;
import com.example.schedule.dto.request.ScheduleShareRequest;
import com.example.schedule.dto.response.ResponseMessage;
import com.example.schedule.dto.response.ScheduleShareResponse;
import com.example.schedule.service.ScheduleShareService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ScheduleShareController {

    private final ScheduleShareService scheduleShareService;

    @PostMapping("/schedules/{scheduleId}/share")
    public ResponseEntity<ResponseMessage> shareSchedule(@PathVariable Long scheduleId,
                                                         @RequestBody ScheduleShareRequest request,
                                                         @RequestParam Long ownerId) {
        scheduleShareService.createShare(scheduleId, request, ownerId);
        return ResponseEntity.ok(ResponseMessage.of("일정이 성공적으로 공유되었습니다.", 200));
    }

    @PatchMapping("/shares/{shareId}/accept")
    public ResponseEntity<ResponseMessage> acceptShare(@PathVariable Long shareId,
                                                       @AuthenticationPrincipal CustomUserDetails userDetails) {
        Long userId = userDetails.getUserId();
        scheduleShareService.acceptShare(shareId, userId);
        return ResponseEntity.ok(ResponseMessage.of("일정 공유를 수락했습니다.", 200));
    }

    @PatchMapping("/shares/{shareId}/decline")
    public ResponseEntity<ResponseMessage> declineShare(@PathVariable Long shareId,
                                                        @AuthenticationPrincipal CustomUserDetails userDetails) {
        Long userId = userDetails.getUserId();
        scheduleShareService.declineShare(shareId, userId);
        return ResponseEntity.ok(ResponseMessage.of("일정 공유를 거절했습니다.", 200));
    }

    @GetMapping("/shares/received")
    public ResponseEntity<List<ScheduleShareResponse>> getReceivedShares(@AuthenticationPrincipal CustomUserDetails userDetails) {
        Long userId = userDetails.getUserId();
        return ResponseEntity.ok(scheduleShareService.getReceivedShares(userId));
    }

    @GetMapping("/shares/sent")
    public ResponseEntity<List<ScheduleShareResponse>> getSentShares(@AuthenticationPrincipal CustomUserDetails userDetails) {
        Long userId = userDetails.getUserId();
        return ResponseEntity.ok(scheduleShareService.getSentShares(userId));
    }
}