package de.inmediasp.skill_orakel.skill_profile.domain_model.skill_profile.service;

import org.springframework.stereotype.Service;

import de.inmediasp.skill_orakel.skill_profile.domain_model.basic_info.db.entity.BasicInfo;
import de.inmediasp.skill_orakel.skill_profile.domain_model.basic_info.model.BasicInfoBusinessObject;
import de.inmediasp.skill_orakel.skill_profile.domain_model.skill_profile.db.entity.SkillProfile;
import de.inmediasp.skill_orakel.skill_profile.domain_model.skill_profile.model.SkillProfileBusinessObject;
import de.inmediasp.skill_orakel.skill_profile.services.get_skill_profile.mappers.EntityToBusinessObjectMapper;

@Service
public class SkillProfileMapper implements EntityToBusinessObjectMapper<SkillProfileBusinessObject, SkillProfile> {

    @Override
    public SkillProfileBusinessObject convertToBusinessObject(SkillProfile skillProfile) {
        if (skillProfile == null) {
            return null;
        }

        BasicInfo basicInfo = skillProfile.getBasicInfo();

        return new SkillProfileBusinessObject(skillProfile.getId(),
                new BasicInfoBusinessObject(basicInfo.getId(), basicInfo.getFirstName(), basicInfo.getLastName()),
                skillProfile.getEmployeeIdentifier());
    }
    
}
