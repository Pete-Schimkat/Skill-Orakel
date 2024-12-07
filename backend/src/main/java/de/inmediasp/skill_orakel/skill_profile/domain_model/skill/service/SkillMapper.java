package de.inmediasp.skill_orakel.skill_profile.domain_model.skill.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import de.inmediasp.skill_orakel.skill_profile.domain_model.skill.db.entities.Skill;
import de.inmediasp.skill_orakel.skill_profile.domain_model.skill.model.SkillBusinessObject;
import de.inmediasp.skill_orakel.skill_profile.services.get_skill_profile.mappers.EntitiesToBusinessObjectsMapper;

@Service
public class SkillMapper implements EntitiesToBusinessObjectsMapper<SkillBusinessObject, Skill>{

    @Override
    public List<SkillBusinessObject> convertToBusinessObjects(List<Skill> entities) {
        return entities.stream()
            .map(this::convertToBusinessObject)
            .toList();
    }

    private SkillBusinessObject convertToBusinessObject(Skill skill){
        return new SkillBusinessObject(skill.getSkillId(), skill.getName());
    }

    @Override
    public List<Skill> convertToEntities(List<SkillBusinessObject> businessObjects, UUID skillProfileId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'convertToEntities'");
    }
    

}
