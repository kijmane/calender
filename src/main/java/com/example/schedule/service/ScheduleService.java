package com.example.schedule.service;

import com.example.schedule.domain.Schedule;
import com.example.schedule.dto.ScheduleRequest;
import com.example.schedule.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    @Autowired
    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }
    // 일정 생성 메서드
    public void createSchedule(ScheduleRequest request) {
        // 전달받은 일정 요청을 바탕으로 Schedule 객체 생성
        Schedule schedule = new Schedule(request.getTitle(), request.getContent(), request.getUser());
        // 생성된 일정 객체를 데이터베이스에 저장
        scheduleRepository.save(schedule);
    }
    // 모든 일정 조회 메서드
    public List<Schedule> getSchedules() {
        return scheduleRepository.findAll();
    }
    // 특정 일정 조회 메서드
    public Schedule getSchedule(Long id) {
        return scheduleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("일정을 찾을 수 없습니다."));
    }
     // 일정 수정 메서드
    public void updateSchedule(Long id, ScheduleRequest request) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("일정을 찾을 수 없습니다."));
        // 수정된 내용 반영
        schedule.setTitle(request.getTitle());
        schedule.setContent(request.getContent());
        // 수정된 일정 저장
        scheduleRepository.save(schedule);
    }
    // 일정 삭제 메서드
    public void deleteSchedule(Long id) {
        scheduleRepository.deleteById(id);
    }
}