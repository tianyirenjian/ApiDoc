package com.tianyisoft.apidoc.controller;

import com.tianyisoft.apidoc.domain.Project;
import com.tianyisoft.apidoc.service.ProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

@RestController
public class ProjectController {
    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/projects")
    public List<Project> index() {
        return projectService.findAll();
    }

    @PostMapping("/projects")
    public ResponseEntity<? extends Serializable> store(@RequestBody @Valid Project project, BindingResult bindingResult) {
        HashMap<String, String> map = new HashMap<>();
        if (bindingResult.hasErrors()) {
            for (ObjectError error: bindingResult.getAllErrors()) {
                map.put("message", error.getDefaultMessage());
                return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
            }
        }
        Project bySlug = projectService.findBySlug(project.getSlug());
        if (bySlug != null) {
            map.put("message", "slug 已被占用");
            return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(projectService.save(project), HttpStatus.OK);
    }
}
