package de.inmediasp.skill_orakel.skill_profile.domain_model.project_assignment.service;

import java.util.List;

import org.springframework.stereotype.Service;

import de.inmediasp.skill_orakel.skill_profile.domain_model.project_assignment.ProjectService;
import de.inmediasp.skill_orakel.skill_profile.domain_model.project_assignment.db.ProjectRepository;
import de.inmediasp.skill_orakel.skill_profile.domain_model.project_assignment.db.entity.project.Project;
import de.inmediasp.skill_orakel.skill_profile.domain_model.project_assignment.model.ProjectBusinessObject;
import de.inmediasp.skill_orakel.skill_profile.services.get_skill_profile.mappers.EntitiesToBusinessObjectsMapper;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository repository;
    private final EntitiesToBusinessObjectsMapper<ProjectBusinessObject, Project> mapper;

    public ProjectServiceImpl(ProjectRepository repository,
            EntitiesToBusinessObjectsMapper<ProjectBusinessObject, Project> mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<ProjectBusinessObject> getAllProjects() {
        return mapper.convertToBusinessObjects(repository.findAll()); 
    }

}
