package com.tianyisoft.apidoc.controller;

import com.tianyisoft.apidoc.domain.Project;
import com.tianyisoft.apidoc.domain.User;
import com.tianyisoft.apidoc.enums.Roles;
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
@RequestMapping("/api")
public class ProjectController extends BaseController {
    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/projects")
    public List<Project> index() {
        User principle = getPrinciple();
        if (principle.getAuthorities().contains(Roles.ADMIN)) {
            return projectService.findAll();
        }
        return projectService.findAllByUserId(principle.getId());
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
        project.setUserId(getPrinciple().getId());
        return new ResponseEntity<>(projectService.save(project), HttpStatus.OK);
    }

    @GetMapping("/projects/{project}")
    public Project show(@PathVariable("project") int project) {
        Project project1 = projectService.find(project);
        if (project1 == null) {
            throw404("项目不存在");
        }
        User user = getPrinciple();
        if (user.getAuthorities().contains(Roles.ADMIN) || project1.getUserId().equals(user.getId())) {
            return project1;
        }
        throw403();
        return null;
    }

    @PutMapping("/projects/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody @Valid Project project, BindingResult bindingResult) {
        Project project1 = projectService.find(id);
        if (project1 == null) {
            throw404("项目不存在");
        }
        checkPermission(project1);
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
        Project project1 = projectService.find(project);
        if (project1 == null) {
            throw404("项目不存在");
        }
        checkPermission(project1);
        projectService.destroy(project);
    }

    private void checkPermission(Project project) {
        User user = getPrinciple();
        if (!(user.getAuthorities().contains(Roles.ADMIN) || project.getUserId().equals(user.getId()))) {
            throw403();
        }
    }
}
