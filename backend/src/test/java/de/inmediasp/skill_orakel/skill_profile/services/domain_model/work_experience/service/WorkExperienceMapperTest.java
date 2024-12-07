package de.inmediasp.skill_orakel.skill_profile.services.domain_model.work_experience.service;

import org.junit.jupiter.api.Test;

import de.inmediasp.skill_orakel.skill_profile.domain_model.work_experience.db.entities.WorkExperience;
import de.inmediasp.skill_orakel.skill_profile.domain_model.work_experience.model.WorkExperienceBusinessObject;
import de.inmediasp.skill_orakel.skill_profile.domain_model.work_experience.service.WorkExperienceMapper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class WorkExperienceMapperTest {

    private final WorkExperienceMapper mapper = new WorkExperienceMapper();

    @Test
    void  GIVEN_listWithProjectAssignment_THEN_convertToBusinessObjects_Returns_correctlyMappedList() {

        String description = "1.d4 d5 c4 c6";
        String employer = "lichess";
        UUID id = UUID.randomUUID();
        LocalDate startDate = LocalDate.of(1756, 5, 18);
        LocalDate endDate = LocalDate.of(1762, 11, 24);

        WorkExperienceBusinessObject workExperienceBusinessObject = new WorkExperienceBusinessObject();
        workExperienceBusinessObject.setDescription(description);
        workExperienceBusinessObject.setId(id);
        workExperienceBusinessObject.setEmployer(employer);
        workExperienceBusinessObject.setEndDate(endDate);
        workExperienceBusinessObject.setStartDate(startDate);

        WorkExperience workExperience = new WorkExperience();
        workExperience.setId(id);
        workExperience.setEmployer(employer);
        workExperience.setStartDate(startDate);
        workExperience.setEndDate(endDate);
        workExperience.setDescription(description);

        List<WorkExperienceBusinessObject> workExperienceBusinessObjects = List.of(workExperienceBusinessObject);
        List<WorkExperience> workExperiences = List.of(workExperience);

        List<WorkExperienceBusinessObject> result = mapper.convertToBusinessObjects(workExperiences);

        assertEquals(workExperienceBusinessObjects, result);
    }

    @Test
    void GIVEN_emptyList_THEN_convertToBusinessObject_RETURNS_emptyList() {

        List<WorkExperience> workExperiences = new ArrayList<>();

        List<WorkExperienceBusinessObject> result = mapper.convertToBusinessObjects(workExperiences);

        assertTrue(result.isEmpty());
    }

    @Test
    void GIVEN_ListContainsNull_THEN_outputListContainsNull() {

        List<WorkExperience> workExperiences = new ArrayList<>();
        workExperiences.add(null);

        List<WorkExperienceBusinessObject> result = mapper.convertToBusinessObjects(workExperiences);

        assertTrue(result.contains(null));
    }

}