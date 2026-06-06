package com.jaimin.portfolio_backend.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ExperienceRequest {

    private String company;

    private String position;

    private String description;

    private LocalDate startDate;

    private LocalDate endDate;

    private Boolean currentlyWorking;
}