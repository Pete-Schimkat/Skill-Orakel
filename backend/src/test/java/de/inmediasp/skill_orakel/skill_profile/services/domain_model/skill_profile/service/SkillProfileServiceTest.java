package de.inmediasp.skill_orakel.skill_profile.services.domain_model.skill_profile.service;

import de.inmediasp.skill_orakel.skill_profile.domain_model.skill_profile.SkillProfileService;
import de.inmediasp.skill_orakel.skill_profile.domain_model.skill_profile.db.SkillProfileRepository;
import de.inmediasp.skill_orakel.skill_profile.domain_model.skill_profile.db.entity.SkillProfile;
import de.inmediasp.skill_orakel.skill_profile.domain_model.skill_profile.model.SkillProfileBusinessObject;
import de.inmediasp.skill_orakel.skill_profile.domain_model.skill_profile.service.SkillProfileMapper;
import de.inmediasp.skill_orakel.skill_profile.domain_model.skill_profile.service.SkillProfileServiceImpl;
import de.inmediasp.skill_orakel.skill_profile.services.get_skill_profile.mappers.EntityToBusinessObjectMapper;
import de.inmediasp.skill_orakel.skill_profile.web.exception_handling.exception.DatabaseAccessException;
import org.junit.jupiter.api.Test;
import org.springframework.dao.DataAccessException;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SkillProfileServiceTest {

    private final SkillProfileRepository repository = mock(SkillProfileRepository.class);
    private final EntityToBusinessObjectMapper<SkillProfileBusinessObject, SkillProfile> mapper
            = mock(SkillProfileMapper.class);
    private final SkillProfileService skillProfileService = new SkillProfileServiceImpl(repository, mapper);

    @Test
    void GIVEN_dbHasEntryWithSkillProfileId_THEN_findById_RETURNS_thatElement() {

        SkillProfile skillProfile = new SkillProfile();
        SkillProfileBusinessObject skillProfileBusinessObject = new SkillProfileBusinessObject();
        Optional<SkillProfile> optional = Optional.of(skillProfile);

        UUID id = UUID.randomUUID();
        when(repository.findById(id)).thenReturn(optional);
        when(mapper.convertToBusinessObject(skillProfile)).thenReturn(skillProfileBusinessObject);

        SkillProfileBusinessObject result = skillProfileService.findById(id);

        assertEquals(skillProfileBusinessObject, result);
    }

    @Test
    void GIVEN_dbHasNoEntryWithSkillProfileId_THEN_findById_RETURNS_null() {

        Optional<SkillProfile> optional = Optional.empty();

        UUID id = UUID.randomUUID();
        when(repository.findById(id)).thenReturn(optional);

        SkillProfileBusinessObject result = skillProfileService.findById(id);

        assertNull(result);
    }

    @Test
    void GIVEN_repositoryThrowsDataAccessException_THEN_DatabaseAccessExceptionWasThrown() {

        UUID id = UUID.randomUUID();
        DataAccessException dataAccessException = mock(DataAccessException.class);
        when(repository.findById(id)).thenThrow(dataAccessException);

        assertThrows(DatabaseAccessException.class, () -> skillProfileService.findById(id));
    }
}