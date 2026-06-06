package com.jaimin.portfolio_backend.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.jaimin.portfolio_backend.dto.ProjectRequest;
import com.jaimin.portfolio_backend.entity.Project;
import com.jaimin.portfolio_backend.repository.ProjectRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;

    public Project createProject(ProjectRequest request) {

        Project project = Project.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .githubUrl(request.getGithubUrl())
                .liveUrl(request.getLiveUrl())
                .imageUrl(request.getImageUrl())
                .technologies(request.getTechnologies())
                .featured(request.getFeatured())
                .createdAt(LocalDateTime.now())
                .build();

        return projectRepository.save(project);
    }

    public List<Project> getAllProjects() {

        return projectRepository.findAll();

    }

public Project getProjectById(Long id) {

    return projectRepository.findById(id)
            .orElseThrow(() ->
                    new RuntimeException(
                            "Project with ID " + id + " not found"));
}

    public Project updateProject(Long id, ProjectRequest request) {

    Project project = projectRepository.findById(id)
            .orElseThrow(() ->
                    new RuntimeException(
                            "Project with ID " + id + " not found"));

    project.setTitle(request.getTitle());
    project.setDescription(request.getDescription());
    project.setGithubUrl(request.getGithubUrl());
    project.setLiveUrl(request.getLiveUrl());
    project.setImageUrl(request.getImageUrl());
    project.setTechnologies(request.getTechnologies());
    project.setFeatured(request.getFeatured());

    return projectRepository.save(project);
}

   public void deleteProject(Long id) {

    Project project = projectRepository.findById(id)
            .orElseThrow(() ->
                    new RuntimeException(
                            "Project with ID " + id + " not found"));

    projectRepository.delete(project);
}

}
