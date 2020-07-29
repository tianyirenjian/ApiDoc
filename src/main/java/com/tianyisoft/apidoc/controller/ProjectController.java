package com.tianyisoft.apidoc.controller;

import com.tianyisoft.apidoc.domain.Project;
import com.tianyisoft.apidoc.repository.ProjectRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProjectController {
    private final ProjectRepository projectRepository;

    public ProjectController(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Cacheable("projects")
    @GetMapping("/projects")
    public List<Project> index() {
        return projectRepository.findAll();
    }

    @CacheEvict(value = "projects", allEntries = true)
    @PostMapping("/projects")
    public Project store(@RequestBody Project project) {
        return projectRepository.save(project);
    }
}
