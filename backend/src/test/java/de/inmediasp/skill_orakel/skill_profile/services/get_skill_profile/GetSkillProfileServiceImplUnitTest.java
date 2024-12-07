package de.inmediasp.skill_orakel.skill_profile.services.get_skill_profile;

import de.inmediasp.skill_orakel.skill_profile.domain_model.basic_info.model.BasicInfoBusinessObject;
import de.inmediasp.skill_orakel.skill_profile.domain_model.industry_knowledge.IndustryKnowledgeService;
import de.inmediasp.skill_orakel.skill_profile.domain_model.language_knowledge.LanguageKnowledgeService;
import de.inmediasp.skill_orakel.skill_profile.domain_model.project_assignment.ProjectAssignmentService;
import de.inmediasp.skill_orakel.skill_profile.domain_model.qualification.QualificationService;
import de.inmediasp.skill_orakel.skill_profile.domain_model.skill.EmployeeSkillService;
import de.inmediasp.skill_orakel.skill_profile.domain_model.skill_profile.SkillProfileService;
import de.inmediasp.skill_orakel.skill_profile.domain_model.skill_profile.model.SkillProfileBusinessObject;
import de.inmediasp.skill_orakel.skill_profile.domain_model.work_experience.WorkExperienceService;
import de.inmediasp.skill_orakel.skill_profile.services.SkillProfileRestService;
import de.inmediasp.skill_orakel.skill_profile.services.get_skill_profile.dto.SkillProfileDTO;
import de.inmediasp.skill_orakel.skill_profile.web.exception_handling.exception.NoPermissionToGetSkillProfileException;
import de.inmediasp.skill_orakel.skill_profile.web.exception_handling.exception.SkillProfileNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.Authentication;

import java.util.ArrayList;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GetSkillProfileServiceImplUnitTest {


    private final QualificationService qualificationService = mock(QualificationService.class);
    private final IndustryKnowledgeService industryKnowledgeService = mock(IndustryKnowledgeService.class);
    private final SkillProfileService skillProfileService = mock(SkillProfileService.class);
    private final LanguageKnowledgeService languageKnowledgeService = mock(LanguageKnowledgeService.class);
    private final WorkExperienceService workExperienceService = mock(WorkExperienceService.class);
    private final ProjectAssignmentService projectAssignmentService = mock(ProjectAssignmentService.class);
    private final EmployeeSkillService employeeSkillService = mock(EmployeeSkillService.class);
    private final SkillProfileAuthorizationService skillProfileAuthorizationService = mock(SkillProfileAuthorizationService.class);
    private final SkillProfileRestService getSkillProfileService = new GetSkillProfileServiceImpl(
            qualificationService,
            industryKnowledgeService,
            skillProfileService,
            languageKnowledgeService,
            workExperienceService,
            projectAssignmentService,
            employeeSkillService,
            skillProfileAuthorizationService
    );

    @Test
    void GIVEN_everythingIsValid_THEN_skillProfileIsReturned() {

        UUID id = UUID.randomUUID();
        BasicInfoBusinessObject basicInfoBusinessObject = new BasicInfoBusinessObject();

        SkillProfileDTO skillProfileDTO = new SkillProfileDTO(id, basicInfoBusinessObject,
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>()
        );

        SkillProfileBusinessObject skillProfileBusinessObject = new SkillProfileBusinessObject();
        skillProfileBusinessObject.setId(id);
        skillProfileBusinessObject.setBasicInfo(basicInfoBusinessObject);

        Authentication authentication = mock(Authentication.class);

        when(qualificationService.findAllBySkillProfileId(id)).thenReturn(new ArrayList<>());
        when(industryKnowledgeService.findBySkillProfileId(id)).thenReturn(new ArrayList<>());
        when(languageKnowledgeService.findBySkillProfileId(id)).thenReturn(new ArrayList<>());
        when(workExperienceService.findBySkillProfileId(id)).thenReturn(new ArrayList<>());
        when(projectAssignmentService.findAllBySkillProfileId(id)).thenReturn(new ArrayList<>());
        when(employeeSkillService.findAllBySkillProfileId(id)).thenReturn(new ArrayList<>());
        when(skillProfileService.findById(id)).thenReturn(skillProfileBusinessObject);

        when(skillProfileAuthorizationService.isAllowedToReadSkillProfile(authentication, skillProfileBusinessObject)).thenReturn(true);

        SkillProfileDTO result = getSkillProfileService.getSkillProfileById(id, authentication);

        assertEquals(skillProfileDTO, result);
    }

    @Test
    void GIVEN_userIsNotAllowedToReadSkillProfile_THEN_ResponseStatusExceptionWasThrown() {

        UUID id = UUID.randomUUID();
        SkillProfileBusinessObject skillProfileBusinessObject = mock(SkillProfileBusinessObject.class);
        Authentication authentication = mock(Authentication.class);

        when(skillProfileService.findById(id)).thenReturn(skillProfileBusinessObject);
        when(skillProfileAuthorizationService.isAllowedToReadSkillProfile(authentication, skillProfileBusinessObject)).thenReturn(false);

        assertThrows(NoPermissionToGetSkillProfileException.class,
                () -> getSkillProfileService.getSkillProfileById(id, authentication));
    }

    @Test
    void GIVEN_skillProfileCanNotBeFound_THEN_ResponseStatusExceptionWasThrown() {

        UUID id = UUID.randomUUID();

        Authentication authentication = mock(Authentication.class);
        when(skillProfileService.findById(id)).thenReturn(null);

        assertThrows(SkillProfileNotFoundException.class,
                () -> getSkillProfileService.getSkillProfileById(id, authentication));
    }

}