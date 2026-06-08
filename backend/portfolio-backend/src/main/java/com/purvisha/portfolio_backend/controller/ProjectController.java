package com.purvisha.portfolio_backend.controller;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.purvisha.portfolio_backend.dto.ProjectRequest;
import com.purvisha.portfolio_backend.entity.Project;
import com.purvisha.portfolio_backend.service.ProjectService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @PostMapping
    public Project createProject(
            @RequestBody ProjectRequest request) {

        return projectService.createProject(request);
    }

    @GetMapping("/{id}")
    public Project getProjectById(@PathVariable Long id) {
        return projectService.getProjectById(id);
    }

    @PutMapping("/{id}")
    public Project updateProject(
            @PathVariable Long id,
            @RequestBody ProjectRequest request) {

        return projectService.updateProject(id, request);
    }

    @DeleteMapping("/{id}")
    public String deleteProject(@PathVariable Long id) {

        projectService.deleteProject(id);

        return "Project Deleted Successfully";
    }

    @GetMapping
public List<Project> getAllProjects() {
    return projectService.getAllProjects();
}

}
