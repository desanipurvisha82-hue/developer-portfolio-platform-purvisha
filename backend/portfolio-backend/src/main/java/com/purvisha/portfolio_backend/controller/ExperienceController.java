package com.purvisha.portfolio_backend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.purvisha.portfolio_backend.dto.ExperienceRequest;
import com.purvisha.portfolio_backend.entity.Experience;
import com.purvisha.portfolio_backend.service.ExperienceService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/experiences")
@RequiredArgsConstructor
public class ExperienceController {

    private final ExperienceService experienceService;

    @PostMapping
    public Experience createExperience(
            @RequestBody ExperienceRequest request) {

        return experienceService.createExperience(request);
    }

    @GetMapping
    public List<Experience> getAllExperiences() {

        return experienceService.getAllExperiences();
    }

    @GetMapping("/{id}")
    public Experience getExperienceById(
            @PathVariable Long id) {

        return experienceService.getExperienceById(id);
    }

    @PutMapping("/{id}")
    public Experience updateExperience(
            @PathVariable Long id,
            @RequestBody ExperienceRequest request) {

        return experienceService.updateExperience(
                id, request);
    }

    @DeleteMapping("/{id}")
    public String deleteExperience(
            @PathVariable Long id) {

        experienceService.deleteExperience(id);

        return "Experience Deleted Successfully";
    }
}