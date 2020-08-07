package com.tianyisoft.apidoc.repository;

import com.tianyisoft.apidoc.domain.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {
    public Project findBySlug(String slug);

    @Query(value = "select * from project where slug = ?1 and id != ?2", nativeQuery = true)
    public Project findBySlugIgnoreId(String slug, int id);
}
