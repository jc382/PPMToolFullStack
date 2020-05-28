package dev.jingweichen.ppmtool.service;

import dev.jingweichen.ppmtool.domain.Project;
import dev.jingweichen.ppmtool.exceptions.ProjectIdException;
import dev.jingweichen.ppmtool.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public Project saveOrUpdateProject(Project project) {
        try {
            project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
            return projectRepository.save(project);
        }
        catch (Exception e) {
            throw new ProjectIdException("Project ID '" +
                    project.getProjectIdentifier().toUpperCase() + "' already exist");
        }
    }
}
