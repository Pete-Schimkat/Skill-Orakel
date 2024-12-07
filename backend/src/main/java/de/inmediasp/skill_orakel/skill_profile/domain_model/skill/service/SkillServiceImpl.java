package de.inmediasp.skill_orakel.skill_profile.domain_model.skill.service;

import java.util.List;

import org.springframework.stereotype.Service;

import de.inmediasp.skill_orakel.skill_profile.domain_model.skill.SkillService;
import de.inmediasp.skill_orakel.skill_profile.domain_model.skill.db.SkillRepository;
import de.inmediasp.skill_orakel.skill_profile.domain_model.skill.db.entities.Skill;
import de.inmediasp.skill_orakel.skill_profile.domain_model.skill.model.SkillBusinessObject;
import de.inmediasp.skill_orakel.skill_profile.services.get_skill_profile.mappers.EntitiesToBusinessObjectsMapper;

@Service
public class SkillServiceImpl implements SkillService {

    private final SkillRepository skillRepository;
    private final EntitiesToBusinessObjectsMapper<SkillBusinessObject, Skill> mapper;

    

    public SkillServiceImpl(SkillRepository skillRepository,
            EntitiesToBusinessObjectsMapper<SkillBusinessObject, Skill> mapper) {
        this.skillRepository = skillRepository;
        this.mapper = mapper;
    }

    @Override
    public List<SkillBusinessObject> getAllSkills() {
        return mapper.convertToBusinessObjects(skillRepository.findAll());
    }
    
}
