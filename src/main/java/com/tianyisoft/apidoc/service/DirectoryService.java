package com.tianyisoft.apidoc.service;

import com.tianyisoft.apidoc.domain.Directory;
import com.tianyisoft.apidoc.repository.DirectoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DirectoryService {
    private final DirectoryRepository directoryRepository;

    public DirectoryService(DirectoryRepository directoryRepository) {
        this.directoryRepository = directoryRepository;
    }

    public List<Directory> findAll(int project) {
        return directoryRepository.findByProjectIdAndParentId(project, null);
    }

    public Directory save(Directory directory) {
        return directoryRepository.save(directory);
    }

    public Directory find(int project, int id) {
        return directoryRepository.findByIdAndProjectId(id, project).orElse(null);
    }
}
