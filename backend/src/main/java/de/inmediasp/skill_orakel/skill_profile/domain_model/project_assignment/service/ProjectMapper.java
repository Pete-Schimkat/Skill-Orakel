package de.inmediasp.skill_orakel.skill_profile.domain_model.project_assignment.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import de.inmediasp.skill_orakel.skill_profile.domain_model.project_assignment.db.entity.project.Project;
import de.inmediasp.skill_orakel.skill_profile.domain_model.project_assignment.model.ProjectBusinessObject;
import de.inmediasp.skill_orakel.skill_profile.services.get_skill_profile.mappers.EntitiesToBusinessObjectsMapper;

@Service
public class ProjectMapper  implements EntitiesToBusinessObjectsMapper<ProjectBusinessObject, Project> {

    @Override
    public List<ProjectBusinessObject> convertToBusinessObjects(List<Project> entities) {
        return entities.stream().map(this::convertToBusinessObject).toList(); 
    }

    private ProjectBusinessObject convertToBusinessObject(Project project) {
        return new ProjectBusinessObject(project.getProjectId(), project.getName(), project.getDescription(), project.getCustomer()); 
    }

    @Override
    public List<Project> convertToEntities(List<ProjectBusinessObject> businessObjects, UUID skillProfileId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'convertToEntities'");
    }
    
}
