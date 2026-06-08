package com.purvisha.portfolio_backend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.purvisha.portfolio_backend.dto.EducationRequest;
import com.purvisha.portfolio_backend.entity.Education;
import com.purvisha.portfolio_backend.service.EducationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/educations")
@RequiredArgsConstructor
public class EducationController {

    private final EducationService educationService;

    @PostMapping
    public Education createEducation(
            @RequestBody EducationRequest request) {

        return educationService.createEducation(request);
    }

    @GetMapping
    public List<Education> getAllEducations() {

        return educationService.getAllEducations();
    }

    @GetMapping("/{id}")
    public Education getEducationById(
            @PathVariable Long id) {

        return educationService.getEducationById(id);
    }

    @PutMapping("/{id}")
    public Education updateEducation(
            @PathVariable Long id,
            @RequestBody EducationRequest request) {

        return educationService.updateEducation(id, request);
    }

    @DeleteMapping("/{id}")
    public String deleteEducation(
            @PathVariable Long id) {

        educationService.deleteEducation(id);

        return "Education Deleted Successfully";
    }
}