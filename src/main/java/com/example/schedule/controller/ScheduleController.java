package com.example.schedule.controller;

import com.example.schedule.dto.request.ScheduleRequest;
import com.example.schedule.dto.request.ScheduleSearchCondition;
import com.example.schedule.dto.response.ResponseMessage;
import com.example.schedule.entity.Schedule;
import com.example.schedule.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping
    public ResponseEntity<ResponseMessage> createSchedule(@RequestBody ScheduleRequest request) {
        scheduleService.createSchedule(request);
        return ResponseEntity.ok(new ResponseMessage("일정이 성공적으로 생성되었습니다."));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Schedule> getSchedule(@PathVariable Long id) {
        return ResponseEntity.ok(scheduleService.getSchedule(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseMessage> updateSchedule(@PathVariable Long id,
                                                          @RequestBody ScheduleRequest request) {
        scheduleService.updateSchedule(id, request);
        return ResponseEntity.ok(new ResponseMessage("일정이 성공적으로 수정되었습니다."));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseMessage> deleteSchedule(@PathVariable Long id) {
        scheduleService.deleteSchedule(id);
        return ResponseEntity.ok(new ResponseMessage("일정이 성공적으로 삭제되었습니다."));
    }

    @GetMapping
    public ResponseEntity<Page<Schedule>> getSchedules(@RequestParam int page, @RequestParam int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Schedule> schedules = scheduleService.getSchedules(pageable);
        return ResponseEntity.ok(schedules);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Schedule>> searchSchedules(@ModelAttribute ScheduleSearchCondition condition) {
        List<Schedule> schedules = scheduleService.searchSchedules(condition);
        return ResponseEntity.ok(schedules);
    }

}