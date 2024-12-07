package de.inmediasp.skill_orakel.skill_profile.web;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.inmediasp.skill_orakel.skill_profile.domain_model.project_assignment.ProjectService;
import de.inmediasp.skill_orakel.skill_profile.domain_model.project_assignment.model.ProjectBusinessObject;

@RestController
@RequestMapping("/api/project")
public class ProjectController {
    private final ProjectService service;

    public ProjectController(ProjectService service) {
        this.service = service;
    } 

    @GetMapping
    public List<ProjectBusinessObject> getAllProjects() {
        return this.service.getAllProjects(); 
    }

    
}
