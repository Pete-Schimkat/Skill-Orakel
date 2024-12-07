package de.inmediasp.skill_orakel.skill_profile.domain_model.skill.db;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import de.inmediasp.skill_orakel.skill_profile.domain_model.skill.db.entities.Skill;


public interface SkillRepository extends JpaRepository<Skill, UUID> {
    
}