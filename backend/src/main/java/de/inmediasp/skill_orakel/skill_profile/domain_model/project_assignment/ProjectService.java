package de.inmediasp.skill_orakel.skill_profile.domain_model.project_assignment;

import java.util.List;

import de.inmediasp.skill_orakel.skill_profile.domain_model.project_assignment.model.ProjectBusinessObject;

public interface ProjectService {
    List<ProjectBusinessObject> getAllProjects(); 
}
