package de.inmediasp.skill_orakel.skill_profile.domain_model.work_experience;

import java.util.List;
import java.util.UUID;

import de.inmediasp.skill_orakel.skill_profile.domain_model.work_experience.model.WorkExperienceBusinessObject;

public interface WorkExperienceService {
    List<WorkExperienceBusinessObject> findBySkillProfileId(UUID id);
    List<WorkExperienceBusinessObject> saveWorkExperiences(List<WorkExperienceBusinessObject> workExperiences, UUID skillProfileId); 
}
