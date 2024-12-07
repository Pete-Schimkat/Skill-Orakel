package de.inmediasp.skill_orakel.skill_profile.domain_model.work_experience.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.inmediasp.skill_orakel.skill_profile.domain_model.work_experience.WorkExperienceService;
import de.inmediasp.skill_orakel.skill_profile.domain_model.work_experience.db.WorkExperienceRepository;
import de.inmediasp.skill_orakel.skill_profile.domain_model.work_experience.db.entities.WorkExperience;
import de.inmediasp.skill_orakel.skill_profile.domain_model.work_experience.model.WorkExperienceBusinessObject;
import de.inmediasp.skill_orakel.skill_profile.services.get_skill_profile.mappers.EntitiesToBusinessObjectsMapper;

@Service
@Transactional
public class WorkExperienceServiceImpl implements WorkExperienceService {

    private final WorkExperienceRepository repository;
    private final EntitiesToBusinessObjectsMapper<WorkExperienceBusinessObject, WorkExperience> mapper;

    public WorkExperienceServiceImpl(WorkExperienceRepository workExperience, 
    EntitiesToBusinessObjectsMapper<WorkExperienceBusinessObject, WorkExperience> mapper) {
        this.repository = workExperience;
        this.mapper = mapper;
    }

    @Override
    public List<WorkExperienceBusinessObject> findBySkillProfileId(UUID id) {
        List<WorkExperience> workExperiences =  repository.findBySkillProfileId(id);

        return mapper.convertToBusinessObjects(workExperiences);
    }

    @Override
    public List<WorkExperienceBusinessObject> saveWorkExperiences(List<WorkExperienceBusinessObject> workExperiences,
            UUID skillProfileId) {
        
            List<WorkExperience> workExperienceEntities = mapper.convertToEntities(workExperiences, skillProfileId);
            repository.deleteAllBySkillProfileId(skillProfileId);
            List<WorkExperience> savedEntries = repository.saveAll(workExperienceEntities); 
            return mapper.convertToBusinessObjects(savedEntries); 
    }
}
