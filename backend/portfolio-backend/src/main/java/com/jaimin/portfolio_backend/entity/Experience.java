package com.jaimin.portfolio_backend.entity;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "experiences")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Experience {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String company;

    private String position;

    @Column(length = 2000)
    private String description;

    private LocalDate startDate;

    private LocalDate endDate;

    private Boolean currentlyWorking;
}