package com.purvisha.portfolio_backend.dto;
 
import lombok.Data;
 
@Data
public class ProjectRequest {
 
    private String title;
    private String description;
    private String githubUrl;
    private String liveUrl;
    private String imageUrl;
    private String technologies;
    private Boolean featured;
}