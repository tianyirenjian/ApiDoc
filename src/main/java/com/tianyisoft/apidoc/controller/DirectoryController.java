package com.tianyisoft.apidoc.controller;

import com.tianyisoft.apidoc.domain.Directory;
import com.tianyisoft.apidoc.service.DirectoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DirectoryController {
    private final DirectoryService directoryService;

    public DirectoryController(DirectoryService directoryService) {
        this.directoryService = directoryService;
    }

    @GetMapping("/projects/{project}/directories")
    public List<Directory> index(@PathVariable("project") int project) {
        return directoryService.findAll(project);
    }
}
