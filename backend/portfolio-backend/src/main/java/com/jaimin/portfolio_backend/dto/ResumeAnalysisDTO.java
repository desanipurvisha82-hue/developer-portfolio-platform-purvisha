package com.jaimin.portfolio_backend.dto;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResumeAnalysisDTO {

    private Integer score;

    private Integer atsScore;

    private List<String> strengths;

    private List<String> weaknesses;

    private List<String> missingKeywords;

    private String recommendation;
}