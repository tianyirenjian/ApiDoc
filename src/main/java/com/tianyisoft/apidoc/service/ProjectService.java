package com.tianyisoft.apidoc.service;

import com.tianyisoft.apidoc.domain.Project;
import com.tianyisoft.apidoc.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {
    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Cacheable("projects")
    public List<Project> findAll() {
        return projectRepository.findAll();
    }

    @CacheEvict(value = "projects", allEntries = true)
    public Project save(Project project) {
        return projectRepository.save(project);
    }

    public Project findBySlug(String slug) {
        return projectRepository.findBySlug(slug);
    }

    public Project findBySlugIgnoreId(String slug, int id) {
        return projectRepository.findBySlugIgnoreId(slug, id);
    }

    @Cacheable("project")
    public Project find(int id) {
        return projectRepository.findById(id).orElse(null);
    }
}
