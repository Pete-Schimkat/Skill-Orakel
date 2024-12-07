package de.inmediasp.skill_orakel.skill_profile.domain_model.basic_info;

import java.util.UUID;

import de.inmediasp.skill_orakel.skill_profile.domain_model.basic_info.model.BasicInfoBusinessObject;

public interface BasicInfoService {
    BasicInfoBusinessObject findById(UUID id);
}
