package com.example.schedule.repository;

import com.example.schedule.entity.Schedule;
import com.example.schedule.entity.ScheduleShare;
import com.example.schedule.entity.enums.ShareStatus;
import com.example.schedule.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ScheduleShareRepository extends JpaRepository<ScheduleShare, Long> {
    List<ScheduleShare> findByOwner(User owner);
    List<ScheduleShare> findByInviteeAndStatus(User invitee, ShareStatus status);
    Optional<ScheduleShare> findByScheduleAndInvitee(Schedule schedule, User invitee);
}
