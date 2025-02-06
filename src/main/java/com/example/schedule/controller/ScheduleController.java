package com.example.schedule.controller;

import com.example.schedule.dto.ScheduleRequest;
import com.example.schedule.domain.Schedule;
import com.example.schedule.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;

    @Autowired
    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createSchedule(@RequestBody ScheduleRequest request) {
        scheduleService.createSchedule(request);
        return ResponseEntity.ok("일정이 성공적으로 생성되었습니다.");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Schedule> getSchedule(@PathVariable Long id) {
        Schedule schedule = scheduleService.getSchedule(id);
        return ResponseEntity.ok(schedule);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateSchedule(@PathVariable Long id, @RequestBody ScheduleRequest request) {
        scheduleService.updateSchedule(id, request);
        return ResponseEntity.ok("일정이 성공적으로 수정되었습니다.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSchedule(@PathVariable Long id) {
        scheduleService.deleteSchedule(id);
        return ResponseEntity.ok("일정이 성공적으로 삭제되었습니다.");
    }

    @GetMapping("/all")
    public ResponseEntity<List<Schedule>> getSchedules() {
        List<Schedule> schedules = scheduleService.getSchedules();
        return ResponseEntity.ok(schedules);
    }
}