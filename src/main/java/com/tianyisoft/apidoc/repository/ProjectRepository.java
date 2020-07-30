package com.tianyisoft.apidoc.repository;

import com.tianyisoft.apidoc.domain.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {
    public Project findBySlug(String slug);
}
