package de.inmediasp.skill_orakel.skill_profile.services.domain_model.basic_info.service;

import de.inmediasp.skill_orakel.skill_profile.domain_model.basic_info.db.entity.BasicInfo;
import de.inmediasp.skill_orakel.skill_profile.domain_model.basic_info.model.BasicInfoBusinessObject;
import de.inmediasp.skill_orakel.skill_profile.domain_model.basic_info.service.BasicInfoMapper;
import de.inmediasp.skill_orakel.skill_profile.services.get_skill_profile.mappers.EntityToBusinessObjectMapper;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class BasicInfoEntityObjectMapperTest {

    private final EntityToBusinessObjectMapper<BasicInfoBusinessObject, BasicInfo> mapper = new BasicInfoMapper();


    @Test
    void GIVEN_emptyBasicInfoObject_THEN_convertToBusinessObject_Returns_empty_BasicInfoBusinessObject() {

        BasicInfo basicInfo = new BasicInfo();
        BasicInfoBusinessObject basicInfoBusinessObject = new BasicInfoBusinessObject();

        BasicInfoBusinessObject result = mapper.convertToBusinessObject(basicInfo);

        assertEquals(basicInfoBusinessObject, result);
    }

    @Test
    void GIVEN_basicInfoObjectWithValues_THEN_convertToBusinessObject_Returns_BusinessObjectWithSameValues() {

        UUID id = UUID.randomUUID();
        BasicInfo basicInfo = new BasicInfo();
        basicInfo.setId(id);
        basicInfo.setFirstName("Patrick");
        basicInfo.setLastName("Star");

        BasicInfoBusinessObject basicInfoBusinessObject = new BasicInfoBusinessObject(id, "Patrick", "Star");

        BasicInfoBusinessObject result = mapper.convertToBusinessObject(basicInfo);

        assertEquals(basicInfoBusinessObject, result);
    }

    @Test
    void GIVEN_basicInfoIsNull_THEN_convertToBusinessObject_Returns_null() {

        BasicInfoBusinessObject result = mapper.convertToBusinessObject(null);
        assertNull(result);
    }
}