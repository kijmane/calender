package com.example.schedule.controller;

import com.example.schedule.entity.Tag;
import com.example.schedule.repository.TagRepository;
import jakarta.validation.constraints.Email;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tags")
@RequiredArgsConstructor
public class TagController {

    private TagRepository tagRepository;

    @PostMapping
    public ResponseEntity<Tag> createTag(@RequestParam String name) {
        Tag tag = tagRepository.save(new Tag(name));
        return ResponseEntity.ok(tag);
    }

    @GetMapping
    public ResponseEntity<?> getAllTags() {
        return ResponseEntity.ok(tagRepository.findAll());
    }
}
