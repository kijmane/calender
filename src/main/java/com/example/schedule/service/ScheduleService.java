package com.example.schedule.service;

import com.example.schedule.dto.request.ScheduleRequest;
import com.example.schedule.dto.request.ScheduleSearchCondition;
import com.example.schedule.dto.response.ScheduleResponse;
import com.example.schedule.entity.Category;
import com.example.schedule.entity.Schedule;
import com.example.schedule.entity.Tag;
import com.example.schedule.repository.CategoryRepository;
import com.example.schedule.repository.ScheduleRepository;
import com.example.schedule.repository.TagRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final CategoryRepository categoryRepository;
    private final TagRepository tagRepository;

    public ScheduleService(ScheduleRepository scheduleRepository,
                           CategoryRepository categoryRepository,
                           TagRepository tagRepository) {
        this.scheduleRepository = scheduleRepository;
        this.categoryRepository = categoryRepository;
        this.tagRepository = tagRepository;
    }

    public void createSchedule(ScheduleRequest request) {
        Schedule schedule = Schedule.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .user(request.getUser())
                .category(categoryRepository.findById(request.getCategoryId())
                        .orElseThrow(() -> new RuntimeException("카테고리를 찾을 수 없습니다.")))
                .tags(tagRepository.findAllById(request.getTagIds()))
                .build();

        scheduleRepository.save(schedule);
    }

    public Page<Schedule> getSchedules(Pageable pageable) {
        return scheduleRepository.findAll(pageable);
    }

    public List<ScheduleResponse> searchSchedules(ScheduleSearchCondition condition) {
        List<Schedule> schedules = scheduleRepository.search(condition);

        return schedules.stream()
                .map(schedule -> ScheduleResponse.builder()
                        .id(schedule.getId())
                        .title(schedule.getTitle())
                        .content(schedule.getContent())
                        .categoryName(schedule.getCategory() != null ? schedule.getCategory().getName() : null)
                        .tagNames(schedule.getTags().stream().map(Tag::getName).toList())
                        .createdBy(schedule.getUser().getEmail())
                        .createdAt(schedule.getCreatedAt())
                        .build())
                .toList();
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

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new RuntimeException("카테고리를 찾을 수 없습니다."));
        List<Tag> tags = tagRepository.findAllById(request.getTagIds());

        schedule.update(request.getTitle(), request.getContent(), category, tags);
    }

    @CacheEvict(value = "schedule", key = "#id")
    public void deleteSchedule(Long id) {
        scheduleRepository.deleteById(id);
    }
}