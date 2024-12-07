package de.inmediasp.skill_orakel.skill_profile.domain_model.skill_profile.db;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import de.inmediasp.skill_orakel.skill_profile.domain_model.skill_profile.db.entity.SkillProfile;

public interface SkillProfileRepository extends JpaRepository<SkillProfile, UUID>{
    
}
