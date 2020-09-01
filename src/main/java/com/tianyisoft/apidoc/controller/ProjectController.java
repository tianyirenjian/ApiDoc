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
@RequestMapping("/api")
public class ProjectController extends BaseController {
    private final ProjectService projectService;
    private ObjectError error;

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
            return getErrors(map, bindingResult);
        }
        Project bySlug = projectService.findBySlug(project.getSlug());
        if (bySlug != null) {
            map.put("message", "slug 已被占用");
            return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(projectService.save(project), HttpStatus.OK);
    }

    @GetMapping("/projects/{project}")
    public Project show(@PathVariable("project") int project) {
        Project project1 = projectService.find(project);
        if (project1 == null) {
            throw404("项目不存在");
        }
        return project1;
    }

    @PutMapping("/projects/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody @Valid Project project, BindingResult bindingResult) {
        Project project1 = projectService.find(id);
        if (project1 == null) {
            throw404("项目不存在");
        }
        HashMap<String, String> map = new HashMap<>();
        if (bindingResult.hasErrors()) {
            return getErrors(map, bindingResult);
        }
        Project bySlug = projectService.findBySlugIgnoreId(project.getSlug(), id);
        if (bySlug != null) {
            map.put("message", "slug 已被占用");
            return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
        }
        project.setId(id);
        return new ResponseEntity<>(projectService.save(project), HttpStatus.OK);
    }

    @DeleteMapping("/projects/{project}")
    public void destroy(@PathVariable("project") int project) {
        projectService.destroy(project);
    }
}
