package de.inmediasp.skill_orakel.skill_profile.services.get_skill_profile;

import de.inmediasp.skill_orakel.skill_profile.domain_model.skill_profile.model.SkillProfileBusinessObject;
import de.inmediasp.skill_orakel.skill_profile.web.exception_handling.exception.NotAuthenticatedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillProfileAuthorizationService {

    private static final String ADMIN_ROLE_VALUE = "Admin";
    private static final String PROJECT_MANAGER_ROLE_VALUE = "Projektmanager";

    protected boolean isAllowedToReadSkillProfile(Authentication authentication, SkillProfileBusinessObject skillProfileBusinessObject) {

        if (authentication == null) {
            throw new NotAuthenticatedException("current user is not authenticated");
        }

        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        String subClaim = oAuth2User.getAttribute("sub");

        if (subClaim == null || subClaim.isEmpty()) {
            throw new NotAuthenticatedException("current user is not authenticated");
        }

        return isAdminOrProjectManager(authentication) || isOwnSkillProfile(subClaim, skillProfileBusinessObject);
    }

    private boolean isOwnSkillProfile(String subClaim, SkillProfileBusinessObject skillProfile) {
        String employeeIdentifier = skillProfile.getEmployeeIdentifier();
        return subClaim.equals(employeeIdentifier);
    }

    private boolean isAdminOrProjectManager(Authentication authentication) {
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        List<String> rolesOfUser = oAuth2User.getAttribute("roles");

        if (rolesOfUser == null) {
            return false;
        }

        return rolesOfUser.contains(ADMIN_ROLE_VALUE) || rolesOfUser.contains(PROJECT_MANAGER_ROLE_VALUE);
    }
}