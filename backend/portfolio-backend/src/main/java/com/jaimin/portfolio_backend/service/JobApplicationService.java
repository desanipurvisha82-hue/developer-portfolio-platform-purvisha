package com.jaimin.portfolio_backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jaimin.portfolio_backend.entity.JobApplication;
import com.jaimin.portfolio_backend.repository.JobApplicationRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JobApplicationService {

    private final JobApplicationRepository repository;

    public List<JobApplication> getAllApplications() {
        return repository.findAll();
    }

    public JobApplication getApplicationById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Job Application not found with id " + id));
    }

    public JobApplication createApplication(JobApplication application) {
        return repository.save(application);
    }

    public JobApplication updateApplication(Long id, JobApplication updatedApp) {
        JobApplication existing = getApplicationById(id);
        existing.setJobTitle(updatedApp.getJobTitle());
        existing.setCompany(updatedApp.getCompany());
        existing.setStatus(updatedApp.getStatus());
        existing.setAppliedDate(updatedApp.getAppliedDate());
        existing.setJobUrl(updatedApp.getJobUrl());
        existing.setInterviewDate(updatedApp.getInterviewDate());
        existing.setNotes(updatedApp.getNotes());
        return repository.save(existing);
    }

    public void deleteApplication(Long id) {
        JobApplication existing = getApplicationById(id);
        repository.delete(existing);
    }
}
