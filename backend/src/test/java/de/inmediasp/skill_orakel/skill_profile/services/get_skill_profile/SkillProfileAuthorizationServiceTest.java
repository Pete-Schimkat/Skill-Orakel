package de.inmediasp.skill_orakel.skill_profile.services.get_skill_profile;

import de.inmediasp.skill_orakel.skill_profile.domain_model.skill_profile.model.SkillProfileBusinessObject;
import de.inmediasp.skill_orakel.skill_profile.web.exception_handling.exception.NotAuthenticatedException;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SkillProfileAuthorizationServiceTest {

    private final SkillProfileAuthorizationService skillProfileAuthorizationService = new SkillProfileAuthorizationService();

    @Test
    void GIVEN_isOwnSkillProfile_THEN_isAllowedToRead_RETURNS_true() {

        String subjectIdentifier = "1. e4 c6";

        UUID id = UUID.randomUUID();
        SkillProfileBusinessObject skillProfileBusinessObject = new SkillProfileBusinessObject();
        skillProfileBusinessObject.setId(id);
        skillProfileBusinessObject.setEmployeeIdentifier(subjectIdentifier);

        Authentication authentication = mock(Authentication.class);
        OAuth2User oAuth2User = mock(OAuth2User.class);

        when(authentication.getPrincipal()).thenReturn(oAuth2User);
        when(oAuth2User.getAttribute("sub")).thenReturn(subjectIdentifier);

        boolean result = skillProfileAuthorizationService.isAllowedToReadSkillProfile(authentication, skillProfileBusinessObject);

        assertTrue(result);
    }

    @Test
    void GIVEN_userIsAdmin_AND_isNotOwnSkillProfile_THEN_isAllowedToRead_RETURNS_true() {

        String subjectIdentifier = "1. e4 c6";
        String otherSubjectIdentifier = "1. e4 e5 2.Nc3 Nf6";
        String roleOfUser = "Admin";
        List<String> roles = List.of(roleOfUser);

        UUID id = UUID.randomUUID();
        SkillProfileBusinessObject skillProfileBusinessObject = new SkillProfileBusinessObject();
        skillProfileBusinessObject.setId(id);
        skillProfileBusinessObject.setEmployeeIdentifier(subjectIdentifier);

        Authentication authentication = mock(Authentication.class);
        OAuth2User oAuth2User = mock(OAuth2User.class);

        when(authentication.getPrincipal()).thenReturn(oAuth2User);
        when(oAuth2User.getAttribute("sub")).thenReturn(otherSubjectIdentifier);
        when(oAuth2User.getAttribute("roles")).thenReturn(roles);

        boolean result = skillProfileAuthorizationService.isAllowedToReadSkillProfile(authentication, skillProfileBusinessObject);

        assertTrue(result);
    }

    @Test
    void GIVEN_userIsNotAdmin_AND_isNotOwnSkillProfile_THEN_isAllowedToRead_RETURNS_false() {
        String subjectIdentifier = "1. e4 c6";
        String otherSubjectIdentifier = "1. e4 e5 2.Nc3 Nf6";
        String roleOfUser = "Mitarbeiter";
        List<String> roles = List.of(roleOfUser);

        UUID id = UUID.randomUUID();
        SkillProfileBusinessObject skillProfileBusinessObject = new SkillProfileBusinessObject();
        skillProfileBusinessObject.setId(id);
        skillProfileBusinessObject.setEmployeeIdentifier(subjectIdentifier);

        Authentication authentication = mock(Authentication.class);
        OAuth2User oAuth2User = mock(OAuth2User.class);

        when(authentication.getPrincipal()).thenReturn(oAuth2User);
        when(oAuth2User.getAttribute("sub")).thenReturn(otherSubjectIdentifier);
        when(oAuth2User.getAttribute("roles")).thenReturn(roles);

        boolean result = skillProfileAuthorizationService.isAllowedToReadSkillProfile(authentication, skillProfileBusinessObject);

        assertFalse(result);
    }

    @Test
    void GIVEN_authenticationIsNull_THEN_isAllowToRead_Throws_NotAuthenticatedException() {

        SkillProfileBusinessObject skillProfileBusinessObject = new SkillProfileBusinessObject();

        assertThrows(NotAuthenticatedException.class, () -> skillProfileAuthorizationService
                .isAllowedToReadSkillProfile(null, skillProfileBusinessObject));
    }

    @Test
    void GIVEN_subClaimIsNull_THEN_isAllowedToRead_Throws_notAuthenticatedException() {

        SkillProfileBusinessObject skillProfileBusinessObject = new SkillProfileBusinessObject();

        Authentication authentication = mock(Authentication.class);
        OAuth2User oAuth2User = mock(OAuth2User.class);

        when(authentication.getPrincipal()).thenReturn(oAuth2User);
        when(oAuth2User.getAttribute("sub")).thenReturn(null);

        assertThrows(NotAuthenticatedException.class, () -> skillProfileAuthorizationService
                .isAllowedToReadSkillProfile(authentication, skillProfileBusinessObject));
    }

}