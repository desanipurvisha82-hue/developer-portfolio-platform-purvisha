package com.jaimin.portfolio_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jaimin.portfolio_backend.entity.Education;

public interface EducationRepository
        extends JpaRepository<Education, Long> {

}