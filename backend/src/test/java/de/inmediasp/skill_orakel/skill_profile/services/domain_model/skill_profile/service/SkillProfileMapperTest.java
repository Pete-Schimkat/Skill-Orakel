package de.inmediasp.skill_orakel.skill_profile.services.domain_model.skill_profile.service;

import org.junit.jupiter.api.Test;

import de.inmediasp.skill_orakel.skill_profile.domain_model.basic_info.db.entity.BasicInfo;
import de.inmediasp.skill_orakel.skill_profile.domain_model.basic_info.model.BasicInfoBusinessObject;
import de.inmediasp.skill_orakel.skill_profile.domain_model.skill_profile.db.entity.SkillProfile;
import de.inmediasp.skill_orakel.skill_profile.domain_model.skill_profile.model.SkillProfileBusinessObject;
import de.inmediasp.skill_orakel.skill_profile.domain_model.skill_profile.service.SkillProfileMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class SkillProfileMapperTest {

    private final SkillProfileMapper mapper = new SkillProfileMapper();

    @Test
    void GIVEN_skillProfileIsNotNul_THEN_convertToBusinessObjectMapsCorrectly() {

        BasicInfo basicInfo = new BasicInfo();

        BasicInfoBusinessObject basicInfoBusinessObject = new BasicInfoBusinessObject();

        SkillProfileBusinessObject skillProfileBusinessObject = new SkillProfileBusinessObject();
        skillProfileBusinessObject.setBasicInfo(basicInfoBusinessObject);

        SkillProfile skillProfile = new SkillProfile();
        skillProfile.setBasicInfo(basicInfo);

        SkillProfileBusinessObject result = mapper.convertToBusinessObject(skillProfile);

        assertEquals(skillProfileBusinessObject, result);
    }

    @Test
    void GIVEN_SkillProfileIsNull_THEN_convertToBusinessObjects_RETURNS_null() {

        SkillProfileBusinessObject result = mapper.convertToBusinessObject(null);
        assertNull(result);
    }

}