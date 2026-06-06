package com.jaimin.portfolio_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JobMatchDTO {

    private Integer matchScore;

    private List<String> matchedSkills;

    private List<String> missingSkills;

    private String recommendation;
}