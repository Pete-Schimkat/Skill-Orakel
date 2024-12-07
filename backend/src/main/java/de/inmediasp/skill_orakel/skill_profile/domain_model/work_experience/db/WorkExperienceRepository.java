package de.inmediasp.skill_orakel.skill_profile.domain_model.work_experience.db;

import org.springframework.data.jpa.repository.JpaRepository;

import de.inmediasp.skill_orakel.skill_profile.domain_model.work_experience.db.entities.WorkExperience;

import java.util.List;
import java.util.UUID;

public interface WorkExperienceRepository extends JpaRepository<WorkExperience, UUID> {

    List<WorkExperience> findBySkillProfileId(UUID id);
    void deleteAllBySkillProfileId(UUID skillProfileId); 
}
