package com.example.schedule.repository;

import com.example.schedule.dto.request.ScheduleSearchCondition;
import com.example.schedule.entity.Schedule;

import java.util.List;

public interface ScheduleRepositoryCustom {
    List<Schedule> search(ScheduleSearchCondition condition);
}