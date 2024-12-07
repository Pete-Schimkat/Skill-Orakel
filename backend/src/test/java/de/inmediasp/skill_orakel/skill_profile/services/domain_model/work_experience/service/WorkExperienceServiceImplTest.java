package de.inmediasp.skill_orakel.skill_profile.services.domain_model.work_experience.service;

import de.inmediasp.skill_orakel.skill_profile.domain_model.work_experience.WorkExperienceService;
import de.inmediasp.skill_orakel.skill_profile.domain_model.work_experience.db.WorkExperienceRepository;
import de.inmediasp.skill_orakel.skill_profile.domain_model.work_experience.db.entities.WorkExperience;
import de.inmediasp.skill_orakel.skill_profile.domain_model.work_experience.model.WorkExperienceBusinessObject;
import de.inmediasp.skill_orakel.skill_profile.domain_model.work_experience.service.WorkExperienceMapper;
import de.inmediasp.skill_orakel.skill_profile.domain_model.work_experience.service.WorkExperienceServiceImpl;
import de.inmediasp.skill_orakel.skill_profile.services.get_skill_profile.mappers.EntitiesToBusinessObjectsMapper;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class WorkExperienceServiceImplTest {

    private final WorkExperienceRepository mockRepository = mock(WorkExperienceRepository.class);
    private final EntitiesToBusinessObjectsMapper<WorkExperienceBusinessObject, WorkExperience> mapper
            = mock(WorkExperienceMapper.class);
    private final WorkExperienceService workExperienceService =
            new WorkExperienceServiceImpl(mockRepository, mapper);

    @Test
    void GIVEN_db_has_entries_with_skillProfileId_THEN_findBySkillProfileId_RETURNS_List_of_elements() {

        List<WorkExperience> workExperiences = List.of(new WorkExperience(), new WorkExperience());
        List<WorkExperienceBusinessObject> workExperienceBusinessObjects =
                List.of(new WorkExperienceBusinessObject(), new WorkExperienceBusinessObject());

        UUID id = UUID.randomUUID();
        when(mockRepository.findBySkillProfileId(id)).thenReturn(workExperiences);
        when(mapper.convertToBusinessObjects(workExperiences)).thenReturn(workExperienceBusinessObjects);

        List<WorkExperienceBusinessObject> result = workExperienceService.findBySkillProfileId(id);

        assertEquals(workExperienceBusinessObjects, result);
    }

    @Test
    void GIVEN_db_has_NO_entries_with_skillProfileId_THEN_findBySkillProfileId_RETURNS_List_of_elements() {

        List<WorkExperience> workExperiences = new ArrayList<>();
        List<WorkExperienceBusinessObject> workExperienceBusinessObjects = new ArrayList<>();

        UUID id = UUID.randomUUID();
        when(mockRepository.findBySkillProfileId(id)).thenReturn(workExperiences);
        when(mapper.convertToBusinessObjects(workExperiences)).thenReturn(workExperienceBusinessObjects);

        List<WorkExperienceBusinessObject> result = workExperienceService.findBySkillProfileId(id);

        assertTrue(result.isEmpty());
    }

}