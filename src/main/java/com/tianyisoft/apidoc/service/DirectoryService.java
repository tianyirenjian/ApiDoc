package com.tianyisoft.apidoc.service;

import com.tianyisoft.apidoc.domain.Directory;
import com.tianyisoft.apidoc.repository.DirectoryRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DirectoryService {
    private final DirectoryRepository directoryRepository;

    public DirectoryService(DirectoryRepository directoryRepository) {
        this.directoryRepository = directoryRepository;
    }

    @Cacheable("directories")
    public List<Directory> findAll(int project) {
        return directoryRepository.findByProjectIdAndParentId(project, null);
    }

    @CacheEvict(value = "directories", key = "#project")
    public Directory save(int project, Directory directory) {
        return directoryRepository.save(directory);
    }

    public Directory find(int project, int id) {
        return directoryRepository.findByIdAndProjectId(id, project).orElse(null);
    }

    @CacheEvict(value = "directories", allEntries = true)
    public void destroy(int id) {
        directoryRepository.deleteById(id);
    }
}
