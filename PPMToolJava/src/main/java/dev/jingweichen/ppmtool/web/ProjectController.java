package dev.jingweichen.ppmtool.web;

import dev.jingweichen.ppmtool.domain.Project;
import dev.jingweichen.ppmtool.service.MapValidationErrorService;
import dev.jingweichen.ppmtool.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @PostMapping("")
    public ResponseEntity<?> createNewProject(@Valid @RequestBody Project newProject, BindingResult result) {

        ResponseEntity<?> errorResponse = mapValidationErrorService.mapValidationService(result);

        if (errorResponse != null) {
            return errorResponse;
        }

        Project project = projectService.saveOrUpdateProject(newProject);
        return new ResponseEntity<Project>(project, HttpStatus.CREATED);
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<?> getProjectById(@PathVariable String projectId) {
        Project project = projectService.findByProjectIdentifier(projectId);
        return new ResponseEntity<Project>(project, HttpStatus.OK);
    }
}
