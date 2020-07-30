package com.tianyisoft.apidoc.service;

import com.tianyisoft.apidoc.domain.Project;
import com.tianyisoft.apidoc.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {
    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public List<Project> findAll() {
        return projectRepository.findAll();
    }

    public Project save(Project project) {
        return projectRepository.save(project);
    }

    public Project findBySlug(String slug) {
        return projectRepository.findBySlug(slug);
    }
}
