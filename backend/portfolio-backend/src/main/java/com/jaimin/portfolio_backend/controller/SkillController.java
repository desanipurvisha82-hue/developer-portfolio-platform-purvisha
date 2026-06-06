package com.jaimin.portfolio_backend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.jaimin.portfolio_backend.dto.SkillRequest;
import com.jaimin.portfolio_backend.entity.Skill;
import com.jaimin.portfolio_backend.service.SkillService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/skills")
@RequiredArgsConstructor
public class SkillController {

    private final SkillService skillService;

    @PostMapping
    public Skill createSkill(@RequestBody SkillRequest request) {
        return skillService.createSkill(request);
    }

    @GetMapping
    public List<Skill> getAllSkills() {
        return skillService.getAllSkills();
    }

    @GetMapping("/{id}")
    public Skill getSkillById(@PathVariable Long id) {
        return skillService.getSkillById(id);
    }

    @PutMapping("/{id}")
    public Skill updateSkill(
            @PathVariable Long id,
            @RequestBody SkillRequest request) {

        return skillService.updateSkill(id, request);
    }

    @DeleteMapping("/{id}")
    public String deleteSkill(@PathVariable Long id) {

        skillService.deleteSkill(id);

        return "Skill Deleted Successfully";
    }
}