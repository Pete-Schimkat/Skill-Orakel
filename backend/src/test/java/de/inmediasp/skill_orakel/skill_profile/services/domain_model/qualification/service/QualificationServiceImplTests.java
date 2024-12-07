package de.inmediasp.skill_orakel.skill_profile.services.domain_model.qualification.service;

import de.inmediasp.skill_orakel.skill_profile.domain_model.qualification.db.QualificationRepository;
import de.inmediasp.skill_orakel.skill_profile.domain_model.qualification.db.entity.Qualification;
import de.inmediasp.skill_orakel.skill_profile.domain_model.qualification.model.QualificationBusinessObject;
import de.inmediasp.skill_orakel.skill_profile.domain_model.qualification.service.QualificationMapper;
import de.inmediasp.skill_orakel.skill_profile.domain_model.qualification.service.QualificationServiceImpl;
import de.inmediasp.skill_orakel.skill_profile.services.get_skill_profile.mappers.EntitiesToBusinessObjectsMapper;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class QualificationServiceImplTests {
    
    private final QualificationRepository mockRepository = mock(QualificationRepository.class);
    private final EntitiesToBusinessObjectsMapper<QualificationBusinessObject, Qualification> mapper =
            mock(QualificationMapper.class);
    private final QualificationServiceImpl service = new QualificationServiceImpl(mockRepository, mapper);

    @Test
    void GIVEN_db_has_entry_with_skillProfileId_THEN_findBySkillProfileId_RETURNS_those_elements() {

        List<Qualification> qualifications = List.of(new Qualification(), new Qualification());
        List<QualificationBusinessObject> qualificationBusinessObjects
                = List.of(new QualificationBusinessObject(), new QualificationBusinessObject());

        UUID id = UUID.randomUUID();
        when(mockRepository.findAllBySkillProfileId(id)).thenReturn(qualifications);
        when(mapper.convertToBusinessObjects(qualifications)).thenReturn(qualificationBusinessObjects);

        List<QualificationBusinessObject> result = service.findAllBySkillProfileId(id);

        assertEquals(qualificationBusinessObjects, result);
    }

    @Test
    void Given_db_has_NO_entry_with_skillProfileId_THEN_findBySkillProfileId_RETURNS_empty_list() {

        List<Qualification> qualifications = new ArrayList<>();
        List<QualificationBusinessObject> qualificationBusinessObjects = new ArrayList<>();

        UUID id = UUID.randomUUID();
        when(mockRepository.findAllBySkillProfileId(id)).thenReturn(qualifications);
        when(mapper.convertToBusinessObjects(qualifications)).thenReturn(qualificationBusinessObjects);

        List<QualificationBusinessObject> result = service.findAllBySkillProfileId(id);

        assertTrue(result.isEmpty());
    }
}
