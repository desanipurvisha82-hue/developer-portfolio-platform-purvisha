package com.jaimin.portfolio_backend.dto;

import lombok.Data;

@Data
public class EducationRequest {

    private String institution;

    private String degree;

    private String fieldOfStudy;

    private Integer startYear;

    private Integer endYear;

    private String grade;
}