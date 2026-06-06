package com.jaimin.portfolio_backend.dto;

import lombok.Data;

@Data
public class CoverLetterRequest {

    private String company;

    private String title;

    private String description;
}