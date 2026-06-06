package com.jaimin.portfolio_backend.dto;

import lombok.Data;

@Data
public class SkillRequest {

    private String name;
    private String category;
    private Integer proficiency;
}