package de.inmediasp.skill_orakel.skill_profile.services.domain_model.skill.service;

import de.inmediasp.skill_orakel.skill_profile.domain_model.skill.EmployeeSkillService;
import de.inmediasp.skill_orakel.skill_profile.domain_model.skill.db.EmployeeSkillRepository;
import de.inmediasp.skill_orakel.skill_profile.domain_model.skill.db.entities.EmployeeSkill;
import de.inmediasp.skill_orakel.skill_profile.domain_model.skill.model.SkillBusinessObject;
import de.inmediasp.skill_orakel.skill_profile.domain_model.skill.service.EmployeeSkillServiceImpl;
import de.inmediasp.skill_orakel.skill_profile.domain_model.skill.service.EmployeeSkillMapper;
import de.inmediasp.skill_orakel.skill_profile.services.get_skill_profile.mappers.EntitiesToBusinessObjectsMapper;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class EmployeeSkillServiceImplTest {
    

    private final EmployeeSkillRepository repository = mock(EmployeeSkillRepository.class);
    private final EntitiesToBusinessObjectsMapper<SkillBusinessObject, EmployeeSkill> mapper = mock(EmployeeSkillMapper.class);
    private final EmployeeSkillService service = new EmployeeSkillServiceImpl(repository, mapper);

    @Test
    void GIVEN_db_has_no_entry_with_skillProfileId_THEN_findBySkillProfileId_RETURNS_empty_list() {
        UUID uuid = UUID.randomUUID();

        List<EmployeeSkill> employeeSkills = new ArrayList<>();
        List<SkillBusinessObject> skillBusinessObjects = new ArrayList<>();

        when(repository.findAllByEmployeeSkillId_SkillProfileId(uuid)).thenReturn(employeeSkills);
        when(mapper.convertToBusinessObjects(employeeSkills)).thenReturn(skillBusinessObjects);

        List<SkillBusinessObject> result = service.findAllBySkillProfileId(uuid);

        assertTrue(result.isEmpty());
    }


    @Test
    void GIVEN_db_has_entries_with_skill_profile_id_THEN_findbySkillProfileId_RETURNS_List_of_skills() {
        UUID uuid = UUID.randomUUID();

        List<EmployeeSkill> employeeSkills = List.of(new EmployeeSkill(), new EmployeeSkill());
        List<SkillBusinessObject> skillBusinessObjects = List.of(new SkillBusinessObject(), new SkillBusinessObject());

        when(repository.findAllByEmployeeSkillId_SkillProfileId(uuid)).thenReturn(employeeSkills);
        when(mapper.convertToBusinessObjects(employeeSkills)).thenReturn(skillBusinessObjects);

        List<SkillBusinessObject> result = service.findAllBySkillProfileId(uuid);

        assertEquals(skillBusinessObjects, result);

    }
}

