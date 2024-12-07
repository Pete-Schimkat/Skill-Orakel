package de.inmediasp.skill_orakel.skill_profile.domain_model.project_assignment;

import java.util.List;
import java.util.UUID;

import de.inmediasp.skill_orakel.skill_profile.domain_model.project_assignment.model.ProjectAssignmentBusinessObject;

public interface ProjectAssignmentService {
    List<ProjectAssignmentBusinessObject> findAllBySkillProfileId(UUID id); 
    List<ProjectAssignmentBusinessObject> saveProjectAssignments(List<ProjectAssignmentBusinessObject> projectAssignments, UUID skillProfileId );
}
