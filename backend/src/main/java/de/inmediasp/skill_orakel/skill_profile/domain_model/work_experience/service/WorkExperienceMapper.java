package de.inmediasp.skill_orakel.skill_profile.domain_model.work_experience.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import de.inmediasp.skill_orakel.skill_profile.domain_model.work_experience.db.entities.WorkExperience;
import de.inmediasp.skill_orakel.skill_profile.domain_model.work_experience.model.WorkExperienceBusinessObject;
import de.inmediasp.skill_orakel.skill_profile.services.get_skill_profile.mappers.EntitiesToBusinessObjectsMapper;

@Service
public class WorkExperienceMapper implements EntitiesToBusinessObjectsMapper<WorkExperienceBusinessObject, WorkExperience>{
    
    @Override
    public List<WorkExperienceBusinessObject> convertToBusinessObjects(List<WorkExperience> entities) {
        return entities.stream().map(this::convertToBusinessObject).toList();  
    }

    private WorkExperienceBusinessObject convertToBusinessObject(WorkExperience model) {

        if (model == null) {
            return null;
        }

        return new WorkExperienceBusinessObject(model.getId(), 
            model.getEmployer(), 
            model.getDescription(), 
            model.getStartDate(), 
            model.getEndDate());
    }

    @Override
    public List<WorkExperience> convertToEntities(List<WorkExperienceBusinessObject> businessObjects,
            UUID skillProfileId) {
        return businessObjects.stream().map(workExperience -> convertToEntity(workExperience, skillProfileId)).toList(); 
            }
        
            private WorkExperience convertToEntity(WorkExperienceBusinessObject workExperience, UUID skillProfileId) {
                WorkExperience workExperienceEntity = new WorkExperience();
                workExperienceEntity.setEmployer(workExperience.getEmployer());
                workExperienceEntity.setDescription(workExperience.getDescription());
                workExperienceEntity.setStartDate(workExperience.getStartDate());
                workExperienceEntity.setEndDate(workExperience.getEndDate());
                workExperienceEntity.setSkillProfileId(skillProfileId);

                return workExperienceEntity;

            }
}
