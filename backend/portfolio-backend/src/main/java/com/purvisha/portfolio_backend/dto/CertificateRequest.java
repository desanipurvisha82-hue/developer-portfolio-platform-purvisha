package com.purvisha.portfolio_backend.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class CertificateRequest {

    private String title;

    private String issuer;

    private LocalDate issueDate;

    private String certificateUrl;
}