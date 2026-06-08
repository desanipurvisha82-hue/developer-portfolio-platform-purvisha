package com.purvisha.portfolio_backend.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "educations")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Education {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String institution;

    private String degree;

    private String fieldOfStudy;

    private Integer startYear;

    private Integer endYear;

    private String grade;
}