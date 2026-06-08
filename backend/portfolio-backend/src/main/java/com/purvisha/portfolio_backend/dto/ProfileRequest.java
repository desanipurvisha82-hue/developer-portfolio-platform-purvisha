package com.purvisha.portfolio_backend.dto;

import lombok.Data;

@Data
public class ProfileRequest {

    private String fullName;

    private String headline;

    private String about;

    private String email;

    private String phone;

    private String location;

    private String linkedinUrl;

    private String githubUrl;

    private String resumeUrl;

    private String profileImageUrl;
}