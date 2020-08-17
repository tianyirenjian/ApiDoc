package com.tianyisoft.apidoc.controller;

import com.tianyisoft.apidoc.domain.Directory;
import com.tianyisoft.apidoc.service.DirectoryService;
import com.tianyisoft.apidoc.service.ProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

@RestController
public class DirectoryController extends BaseController {
    private final DirectoryService directoryService;
    private final ProjectService projectService;

    public DirectoryController(DirectoryService directoryService, ProjectService projectService) {
        this.directoryService = directoryService;
        this.projectService = projectService;
    }

    @GetMapping("/projects/{project}/directories")
    public List<Directory> index(@PathVariable("project") int project) {
        if (projectService.find(project) == null) {
            throw404("项目不存在");
        }
        return directoryService.findAll(project);
    }

    @PostMapping("/projects/{project}/directories")
    public ResponseEntity<? extends Serializable> store(@PathVariable("project") int project, @RequestBody @Valid Directory directory, BindingResult bindingResult) {
        HashMap<String, String> map = new HashMap<>();
        if (bindingResult.hasErrors()) {
            return getErrors(map, bindingResult);
        }
        if (projectService.find(project) == null) {
            throw404("项目不存在");
        }
        return new ResponseEntity<>(directoryService.save(project, directory), HttpStatus.OK);
    }

    @GetMapping("/projects/{project}/directories/{directory}")
    public Directory show(@PathVariable("project") int project, @PathVariable("directory") int directory) {
        Directory directory1 = directoryService.find(project, directory);
        if (directory1 == null) {
            throw404("目录不存在");
        }
        return directory1;
    }
}
