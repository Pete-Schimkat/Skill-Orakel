package de.inmediasp.skill_orakel.skill_profile.services;

import de.inmediasp.skill_orakel.skill_profile.services.get_skill_profile.dto.SkillProfileDTO;
import org.springframework.security.core.Authentication;

import java.util.UUID;

public interface SkillProfileRestService {
    SkillProfileDTO getSkillProfileById(UUID id, Authentication authentication);
    SkillProfileDTO putSkillProfileById(UUID skillProfileId, SkillProfileDTO skillProfileDTO);
}
