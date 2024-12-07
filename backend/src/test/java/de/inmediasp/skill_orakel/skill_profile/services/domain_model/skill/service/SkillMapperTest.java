package de.inmediasp.skill_orakel.skill_profile.services.domain_model.skill.service;

import org.junit.jupiter.api.Test;

import de.inmediasp.skill_orakel.skill_profile.domain_model.skill.db.entities.EmployeeSkill;
import de.inmediasp.skill_orakel.skill_profile.domain_model.skill.db.entities.Skill;
import de.inmediasp.skill_orakel.skill_profile.domain_model.skill.model.SkillBusinessObject;
import de.inmediasp.skill_orakel.skill_profile.domain_model.skill.service.EmployeeSkillMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SkillMapperTest {

    private final EmployeeSkillMapper mapper = new EmployeeSkillMapper();

    @Test
    void GIVEN_listWithOneElement_THEN_elementIsMappedCorrectly() {

        UUID id = UUID.randomUUID();
        String name = "Caro Kann Verteidigung";

        Skill skill = new Skill();
        skill.setSkillId(id);
        skill.setName(name);

        EmployeeSkill employeeSkill = new EmployeeSkill();
        employeeSkill.setSkill(skill);

        SkillBusinessObject skillBusinessObject = new SkillBusinessObject();
        skillBusinessObject.setId(id);
        skillBusinessObject.setName(name);

        List<SkillBusinessObject> skillBusinessObjects = List.of(skillBusinessObject);
        List<EmployeeSkill> employeeSkills = List.of(employeeSkill);

        List<SkillBusinessObject> result = mapper.convertToBusinessObjects(employeeSkills);

        assertEquals(skillBusinessObjects, result);
    }

    @Test
    void GIVEN_emptyList_THEN_convertToBusinessObjects_RETURNS_emptyList() {

        List<EmployeeSkill> employeeSkills = new ArrayList<>();

        List<SkillBusinessObject> result = mapper.convertToBusinessObjects(employeeSkills);

        assertTrue(result.isEmpty());
    }

    @Test
    void GIVEN_ListContainsNull_THEN_outListContainsNull() {

        List<EmployeeSkill> employeeSkills = new ArrayList<>();
        employeeSkills.add(null);

        List<SkillBusinessObject> result = mapper.convertToBusinessObjects(employeeSkills);

        assertTrue(result.contains(null));
    }

}