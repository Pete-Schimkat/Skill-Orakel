package de.inmediasp.skill_orakel.skill_profile.domain_model.skill.service;

import de.inmediasp.skill_orakel.skill_profile.domain_model.skill.EmployeeSkillService;
import de.inmediasp.skill_orakel.skill_profile.domain_model.skill.db.EmployeeSkillRepository;
import de.inmediasp.skill_orakel.skill_profile.domain_model.skill.db.entities.EmployeeSkill;
import de.inmediasp.skill_orakel.skill_profile.domain_model.skill.model.SkillBusinessObject;
import de.inmediasp.skill_orakel.skill_profile.services.get_skill_profile.mappers.EntitiesToBusinessObjectsMapper;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EmployeeSkillServiceImpl implements EmployeeSkillService {

    private final EmployeeSkillRepository repository;
    private final EntitiesToBusinessObjectsMapper<SkillBusinessObject, EmployeeSkill> mapper;

    public EmployeeSkillServiceImpl(EmployeeSkillRepository repository,
            EntitiesToBusinessObjectsMapper<SkillBusinessObject, EmployeeSkill> mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<SkillBusinessObject> findAllBySkillProfileId(UUID id) {
        List<EmployeeSkill> employeeSkills = repository.findAllByEmployeeSkillId_SkillProfileId(id);

        return mapper.convertToBusinessObjects(employeeSkills);
    }
}
