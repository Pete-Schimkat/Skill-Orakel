package de.inmediasp.skill_orakel.skill_profile.domain_model.skill;

import java.util.List;
import java.util.UUID;

import de.inmediasp.skill_orakel.skill_profile.domain_model.skill.model.SkillBusinessObject;


public interface EmployeeSkillService {
    List<SkillBusinessObject> findAllBySkillProfileId(UUID id);
}
