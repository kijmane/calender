package com.example.schedule.service;

import com.example.schedule.dto.request.ScheduleShareRequest;
import com.example.schedule.dto.response.ScheduleShareResponse;
import com.example.schedule.entity.*;
import com.example.schedule.entity.enums.ShareStatus;
import com.example.schedule.exception.UserNotFoundException;
import com.example.schedule.repository.ScheduleRepository;
import com.example.schedule.repository.ScheduleShareRepository;
import com.example.schedule.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional
@Service
public class ScheduleShareService {

    private final ScheduleShareRepository shareRepository;
    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    public void createShare(Long scheduleId, ScheduleShareRequest request, Long ownerId) {
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new RuntimeException("일정을 찾을 수 없습니다."));
        User owner = userRepository.findById(ownerId)
                .orElseThrow(() -> new UserNotFoundException("공유자 정보를 찾을 수 없습니다."));
        User invitee = userRepository.findById(request.getInviteeUserId())
                .orElseThrow(() -> new UserNotFoundException("초대받는 유저를 찾을 수 없습니다."));

        if (shareRepository.findByScheduleAndInvitee(schedule, invitee).isPresent()) {
            throw new RuntimeException("이미 공유 요청을 보낸 사용자입니다.");
        }

        ScheduleShare share = ScheduleShare.builder()
                .schedule(schedule)
                .owner(owner)
                .invitee(invitee)
                .status(ShareStatus.PENDING)
                .permission(request.getPermission())
                .invitedAt(LocalDateTime.now())
                .build();

        shareRepository.save(share);
    }

    public void acceptShare(Long shareId, Long userId) {
        ScheduleShare share = getMyInvitation(shareId, userId);
        share.accept();
    }

    public void declineShare(Long shareId, Long userId) {
        ScheduleShare share = getMyInvitation(shareId, userId);
        share.decline();
    }

    public List<ScheduleShareResponse> getReceivedShares(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("유저를 찾을 수 없습니다."));

        return shareRepository.findByInviteeAndStatus(user, ShareStatus.ACCEPTED).stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public List<ScheduleShareResponse> getSentShares(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("유저를 찾을 수 없습니다."));

        return shareRepository.findByOwner(user).stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    private ScheduleShare getMyInvitation(Long shareId, Long userId) {
        ScheduleShare share = shareRepository.findById(shareId)
                .orElseThrow(() -> new RuntimeException("공유 요청을 찾을 수 없습니다."));
        if (!share.getInvitee().getId().equals(userId)) {
            throw new RuntimeException("본인의 초대가 아닙니다.");
        }
        return share;
    }

    private ScheduleShareResponse toDto(ScheduleShare share) {
        return ScheduleShareResponse.builder()
                .shareId(share.getId())
                .scheduleId(share.getSchedule().getId())
                .scheduleTitle(share.getSchedule().getTitle())
                .inviteeEmail(share.getInvitee().getEmail())
                .permission(share.getPermission())
                .status(share.getStatus())
                .invitedAt(share.getInvitedAt())
                .build();
    }
}