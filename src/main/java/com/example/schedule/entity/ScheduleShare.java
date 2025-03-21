package com.example.schedule.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ScheduleShare {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Schedule schedule;

    @ManyToOne(fetch = FetchType.LAZY)
    private User owner;

    @ManyToOne(fetch = FetchType.LAZY)
    private User invitee;

    @Enumerated(EnumType.STRING)
    private ShareStatus status;

    @Enumerated(EnumType.STRING)
    private SharePermission permission;

    private LocalDateTime invitedAt;

    public void accept() {
        this.status = ShareStatus.ACCEPTED;
    }

    public void decline() {
        this.status = ShareStatus.DECLINED;
    }
}
