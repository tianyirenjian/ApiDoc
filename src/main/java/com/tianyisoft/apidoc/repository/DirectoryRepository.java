package com.tianyisoft.apidoc.repository;

import com.tianyisoft.apidoc.domain.Directory;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Optional;

@Repository
public interface DirectoryRepository extends JpaRepository<Directory, Integer> {
    @EntityGraph(attributePaths = "directoryList")
    public List<Directory> findByProjectIdAndParentId(@NotBlank Integer projectId, Integer parentId);

    public Optional<Directory> findByIdAndProjectId(Integer id, Integer projectId);
}
