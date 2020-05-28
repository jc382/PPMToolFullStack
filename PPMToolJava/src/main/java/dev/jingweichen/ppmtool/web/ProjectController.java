package dev.jingweichen.ppmtool.web;

import dev.jingweichen.ppmtool.domain.Project;
import dev.jingweichen.ppmtool.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @PostMapping("")
    public ResponseEntity<Project> createNewProject(@RequestBody Project newProject) {
        Project project = projectService.saveOrUpdateProject(newProject);
        return new ResponseEntity<Project>(project, HttpStatus.CREATED);
    }
}
