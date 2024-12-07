package de.inmediasp.skill_orakel.skill_profile.web;

import de.inmediasp.skill_orakel.skill_profile.services.SkillProfileRestService;
import de.inmediasp.skill_orakel.skill_profile.services.get_skill_profile.dto.SkillProfileDTO;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.Authentication;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

class SkillProfileControllerTest {

    private final SkillProfileRestService mockService = mock(SkillProfileRestService.class);
    private final SkillProfileController skillProfileController = new SkillProfileController(mockService);

    @Test
    void GIVEN_controllerCalledWithAuthenticationAndId_THEN_dtoIsReturned() {
        UUID id = UUID.randomUUID();
        Authentication authentication = mock(Authentication.class);
        SkillProfileDTO skillProfileDTO = new SkillProfileDTO();

        doReturn(skillProfileDTO).when(mockService).getSkillProfileById(id, authentication);
        SkillProfileDTO result = skillProfileController.getSkillProfile(id, authentication);

        assertEquals(skillProfileDTO, result);
    }

}