package de.inmediasp.skill_orakel.skill_profile.domain_model.skill_profile;

import java.util.UUID;

import de.inmediasp.skill_orakel.skill_profile.domain_model.skill_profile.model.SkillProfileBusinessObject;

public interface SkillProfileService {
    SkillProfileBusinessObject findById(UUID id);
}
