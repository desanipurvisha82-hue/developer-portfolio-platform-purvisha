package com.jaimin.portfolio_backend.dto;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SkillMatchResult {

    private Integer score;

    private List<String> matchedSkills;

    private List<String> missingSkills;

    private String recommendation;

    private String roadmap;
}