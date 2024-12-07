package de.inmediasp.skill_orakel.skill_profile.domain_model.basic_info.service;

import org.springframework.stereotype.Service;

import de.inmediasp.skill_orakel.skill_profile.domain_model.basic_info.db.entity.BasicInfo;
import de.inmediasp.skill_orakel.skill_profile.domain_model.basic_info.model.BasicInfoBusinessObject;
import de.inmediasp.skill_orakel.skill_profile.services.get_skill_profile.mappers.EntityToBusinessObjectMapper;

@Service
public class BasicInfoMapper implements EntityToBusinessObjectMapper<BasicInfoBusinessObject, BasicInfo> {

    @Override
    public BasicInfoBusinessObject convertToBusinessObject(BasicInfo basicInfo) {
        if (basicInfo == null) {
            return null;
        }

        return new  BasicInfoBusinessObject(
            basicInfo.getId(), 
            basicInfo.getFirstName(),
            basicInfo.getLastName()
        );
    }

}
