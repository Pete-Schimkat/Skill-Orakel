package de.inmediasp.skill_orakel.skill_profile.domain_model.skill;

import java.util.List;

import de.inmediasp.skill_orakel.skill_profile.domain_model.skill.model.SkillBusinessObject;

public interface SkillService {
    
    List<SkillBusinessObject> getAllSkills();
}
