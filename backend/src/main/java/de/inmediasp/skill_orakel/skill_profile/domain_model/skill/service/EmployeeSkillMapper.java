package de.inmediasp.skill_orakel.skill_profile.domain_model.skill.service;

import de.inmediasp.skill_orakel.skill_profile.domain_model.skill.db.entities.EmployeeSkill;
import de.inmediasp.skill_orakel.skill_profile.domain_model.skill.model.SkillBusinessObject;
import de.inmediasp.skill_orakel.skill_profile.services.get_skill_profile.mappers.EntitiesToBusinessObjectsMapper;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EmployeeSkillMapper implements EntitiesToBusinessObjectsMapper<SkillBusinessObject, EmployeeSkill> {

    @Override
    public List<SkillBusinessObject> convertToBusinessObjects(List<EmployeeSkill> entities) {

        return entities.stream()
                .map(this::convertToBusinessObject)
                .toList();
    }

    private SkillBusinessObject convertToBusinessObject(EmployeeSkill employeeSkill) {
        if (employeeSkill == null)
            return null;

        return new SkillBusinessObject(employeeSkill.getSkill().getSkillId(),
                employeeSkill.getSkill().getName());
    }

    @Override
    public List<EmployeeSkill> convertToEntities(List<SkillBusinessObject> businessObjects, UUID skillProfileId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'convertToEntities'");
    }
}
