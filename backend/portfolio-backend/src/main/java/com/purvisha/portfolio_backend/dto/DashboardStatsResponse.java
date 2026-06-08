package com.purvisha.portfolio_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DashboardStatsResponse {

    private Long projects;
    private Long skills;
    private Long experiences;
    private Long educations;
    private Long certificates;
    private Long applications;
    private Integer profileScore;
    private Integer atsScore;
}