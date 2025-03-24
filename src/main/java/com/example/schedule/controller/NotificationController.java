package com.example.schedule.controller;

import com.example.schedule.entity.Notification;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.*;
import com.example.schedule.dto.request.NotificationRequest;
import com.example.schedule.service.NotificationService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    @MessageMapping("/notifications/send")
    public void sendNotification(NotificationRequest request) {
        notificationService.sendNotification(request);
    }

    @GetMapping
    public ResponseEntity<List<Notification>> getMyNotifications(@RequestParam String receiver) {
        List<Notification> notifications = notificationService.getNotifications(receiver);
        return ResponseEntity.ok(notifications);
    }

    @PatchMapping("/{id}/read")
    public ResponseEntity<String> markAsRead(@PathVariable Long id) {
        notificationService.markAsRead(id);
        return ResponseEntity.ok("알림을 읽음 처리했습니다.");
    }
}
