package com.jaimin.portfolio_backend.repository;

import com.jaimin.portfolio_backend.entity.JobApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface JobApplicationRepository
        extends JpaRepository<JobApplication, Long> {
}
