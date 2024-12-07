package de.inmediasp.skill_orakel.skill_profile.domain_model.project_assignment.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import de.inmediasp.skill_orakel.skill_profile.domain_model.project_assignment.ProjectAssignmentService;
import de.inmediasp.skill_orakel.skill_profile.domain_model.project_assignment.db.ProjectAssignmentRepository;
import de.inmediasp.skill_orakel.skill_profile.domain_model.project_assignment.db.entity.project_assignment.ProjectAssignment;
import de.inmediasp.skill_orakel.skill_profile.domain_model.project_assignment.model.ProjectAssignmentBusinessObject;
import de.inmediasp.skill_orakel.skill_profile.services.get_skill_profile.mappers.EntitiesToBusinessObjectsMapper;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProjectAssignmentServiceImpl implements ProjectAssignmentService {

    private final ProjectAssignmentRepository repository;
    private final EntitiesToBusinessObjectsMapper<ProjectAssignmentBusinessObject, ProjectAssignment> mapper;

    public ProjectAssignmentServiceImpl(ProjectAssignmentRepository repository,
            EntitiesToBusinessObjectsMapper<ProjectAssignmentBusinessObject, ProjectAssignment> mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<ProjectAssignmentBusinessObject> findAllBySkillProfileId(UUID id) {
        List<ProjectAssignment> projectAssignments = repository.findAllByProjectAssignmentId_SkillProfileId(id);
        return mapper.convertToBusinessObjects(projectAssignments);
    }

    @Override
    public List<ProjectAssignmentBusinessObject> saveProjectAssignments(List<ProjectAssignmentBusinessObject> projectAssignments, UUID skillProfileId) {

        List<ProjectAssignment> projectAssignmentEntities = mapper.convertToEntities(projectAssignments,
                skillProfileId);
        repository.deleteAllByProjectAssignmentId_SkillProfileId(skillProfileId);
        List<ProjectAssignment> savedEntries = repository.saveAll(projectAssignmentEntities);
        return mapper.convertToBusinessObjects(savedEntries); 
    }

}