package com.example.schedule.service;

import com.example.schedule.dto.request.ScheduleSearchCondition;
import com.example.schedule.entity.Schedule;
import com.example.schedule.dto.request.ScheduleRequest;
import com.example.schedule.repository.ScheduleRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    public void createSchedule(ScheduleRequest request) {
        Schedule schedule = Schedule.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .user(request.getUser())
                .build();

        scheduleRepository.save(schedule);
    }

    public Page<Schedule> getSchedules(Pageable pageable) {
        return scheduleRepository.findAll(pageable);
    }

    public List<Schedule> searchSchedules(ScheduleSearchCondition condition) {
        return scheduleRepository.search(condition);
    }

    @Cacheable(value = "schedule", key = "#id")
    public Schedule getSchedule(Long id) {
        return scheduleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("일정을 찾을 수 없습니다."));
    }

    @CacheEvict(value = "schedule", key = "#id")
    public void updateSchedule(Long id, ScheduleRequest request) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("일정을 찾을 수 없습니다."));

        schedule.update(request.getTitle(), request.getContent(), null, null);
    }

    @CacheEvict(value = "schedule", key = "#id")
    public void deleteSchedule(Long id) {
        scheduleRepository.deleteById(id);
    }
}