package de.inmediasp.skill_orakel.skill_profile.domain_model.skill.db;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import de.inmediasp.skill_orakel.skill_profile.domain_model.skill.db.entities.EmployeeSkill;
import de.inmediasp.skill_orakel.skill_profile.domain_model.skill.db.entities.EmployeeSkillId;

public interface EmployeeSkillRepository extends JpaRepository<EmployeeSkill, EmployeeSkillId> {

    List<EmployeeSkill> findAllByEmployeeSkillId_SkillProfileId(UUID id);
    
}
