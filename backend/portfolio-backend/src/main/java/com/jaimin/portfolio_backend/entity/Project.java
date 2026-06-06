package com.jaimin.portfolio_backend.entity;
 
import java.time.LocalDateTime;
 
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
 
@Entity
@Table(name = "projects")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Project {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
 
    private String title;
 
    @Column(length = 5000)
    private String description;
 
    private String githubUrl;
 
    private String liveUrl;
 
    private String imageUrl;
 
    private String technologies;
 
    private Boolean featured;
 
    private LocalDateTime createdAt;
}
 