package com.example.schedule.service;

import com.example.schedule.dto.request.NotificationRequest;
import com.example.schedule.dto.response.NotificationResponse;
import com.example.schedule.entity.Notification;
import com.example.schedule.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationService {

    private final SimpMessagingTemplate messagingTemplate;
    private final NotificationRepository notificationRepository;

    public void sendNotification(NotificationRequest request) {
        messagingTemplate.convertAndSendToUser(
                request.getReceiver(),
                "/queue/notifications",
                new NotificationResponse(request.getReceiver(), request.getContent())
        );

        Notification notification = Notification.builder()
                .receiver(request.getReceiver())
                .content(request.getContent())
                .read(false)
                .build();
        notificationRepository.save(notification);

        log.info("알림 전송 완료 → to={}, content={}", request.getReceiver(), request.getContent());
    }

    public List<Notification> getNotifications(String receiver) {
        return notificationRepository.findByReceiver(receiver);
    }

    public void markAsRead(Long id) {
        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("알림을 찾을 수 없습니다."));
        notification.markAsRead();
        notificationRepository.save(notification);
    }
}