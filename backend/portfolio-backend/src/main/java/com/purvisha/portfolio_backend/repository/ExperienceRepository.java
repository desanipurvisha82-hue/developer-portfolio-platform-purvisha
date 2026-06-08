package com.purvisha.portfolio_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.purvisha.portfolio_backend.entity.Experience;

public interface ExperienceRepository
        extends JpaRepository<Experience, Long> {

}