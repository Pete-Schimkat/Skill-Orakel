package de.inmediasp.skill_orakel.skill_profile.services.get_skill_profile;

import java.util.List;
import java.util.UUID;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import de.inmediasp.skill_orakel.skill_profile.domain_model.industry_knowledge.IndustryKnowledgeService;
import de.inmediasp.skill_orakel.skill_profile.domain_model.industry_knowledge.model.IndustryBusinessObject;
import de.inmediasp.skill_orakel.skill_profile.domain_model.language_knowledge.LanguageKnowledgeService;
import de.inmediasp.skill_orakel.skill_profile.domain_model.language_knowledge.model.LanguageKnowledgeBusinessObject;
import de.inmediasp.skill_orakel.skill_profile.domain_model.project_assignment.ProjectAssignmentService;
import de.inmediasp.skill_orakel.skill_profile.domain_model.project_assignment.model.ProjectAssignmentBusinessObject;
import de.inmediasp.skill_orakel.skill_profile.domain_model.qualification.QualificationService;
import de.inmediasp.skill_orakel.skill_profile.domain_model.qualification.model.QualificationBusinessObject;
import de.inmediasp.skill_orakel.skill_profile.domain_model.skill.EmployeeSkillService;
import de.inmediasp.skill_orakel.skill_profile.domain_model.skill.model.SkillBusinessObject;
import de.inmediasp.skill_orakel.skill_profile.domain_model.skill_profile.SkillProfileService;
import de.inmediasp.skill_orakel.skill_profile.domain_model.skill_profile.model.SkillProfileBusinessObject;
import de.inmediasp.skill_orakel.skill_profile.domain_model.work_experience.WorkExperienceService;
import de.inmediasp.skill_orakel.skill_profile.domain_model.work_experience.model.WorkExperienceBusinessObject;
import de.inmediasp.skill_orakel.skill_profile.services.SkillProfileRestService;
import de.inmediasp.skill_orakel.skill_profile.services.get_skill_profile.dto.SkillProfileDTO;
import de.inmediasp.skill_orakel.skill_profile.web.exception_handling.exception.NoPermissionToGetSkillProfileException;
import de.inmediasp.skill_orakel.skill_profile.web.exception_handling.exception.SkillProfileNotFoundException;

@Service
public class GetSkillProfileServiceImpl implements SkillProfileRestService {

    private final QualificationService qualificationService;
    private final IndustryKnowledgeService industryKnowledgeService;
    private final SkillProfileService skillProfileService;
    private final LanguageKnowledgeService languageKnowledgeService;
    private final WorkExperienceService workExperienceService;
    private final ProjectAssignmentService projectAssignmentService;
    private final EmployeeSkillService employeeSkillService;
    private final SkillProfileAuthorizationService skillProfileAuthorizationService;

    public GetSkillProfileServiceImpl(QualificationService qualificationService,
                                      IndustryKnowledgeService industryKnowledgeService,
                                      SkillProfileService skillProfileService,
                                      LanguageKnowledgeService languageKnowledgeService,
                                      WorkExperienceService workExperienceService,
                                      ProjectAssignmentService projectAssignmentService,
                                      EmployeeSkillService employeeSkillService,
                                      SkillProfileAuthorizationService skillProfileAuthorizationService
                                     ) {
        this.qualificationService = qualificationService;
        this.industryKnowledgeService = industryKnowledgeService;
        this.skillProfileService = skillProfileService;
        this.languageKnowledgeService = languageKnowledgeService;
        this.workExperienceService = workExperienceService;
        this.projectAssignmentService = projectAssignmentService;
        this.employeeSkillService = employeeSkillService;
        this.skillProfileAuthorizationService = skillProfileAuthorizationService;
    }

    public SkillProfileDTO getSkillProfileById(UUID skillProfileId, Authentication authentication) {
        SkillProfileBusinessObject skillProfile = skillProfileService.findById(skillProfileId);

        if (skillProfile == null) {
            throw new SkillProfileNotFoundException("Could not find a profile with id: " + skillProfileId);
        }

        boolean isAllowedToReadSkillProfile = skillProfileAuthorizationService.isAllowedToReadSkillProfile(authentication, skillProfile);

        if (!isAllowedToReadSkillProfile) {
            throw new NoPermissionToGetSkillProfileException("Current user is not allowed to access this ressource");
        }

        List<QualificationBusinessObject> qualificationDTOs = qualificationService.findAllBySkillProfileId(skillProfileId);
        List<IndustryBusinessObject> industries = industryKnowledgeService.findBySkillProfileId(skillProfileId);
        List<LanguageKnowledgeBusinessObject> languageKnowledgeDTOs = languageKnowledgeService.findBySkillProfileId(skillProfileId);
        List<WorkExperienceBusinessObject> workExperienceDTOs = workExperienceService.findBySkillProfileId(skillProfileId);
        List<ProjectAssignmentBusinessObject> projectAssignmentsDTOs = projectAssignmentService.findAllBySkillProfileId(skillProfileId);
        List<SkillBusinessObject> skills = employeeSkillService.findAllBySkillProfileId(skillProfileId);

        return new SkillProfileDTO(
            skillProfileId,
            skillProfile.getBasicInfo(),
            qualificationDTOs,
            industries,
            languageKnowledgeDTOs,
            skills,
            workExperienceDTOs,
            projectAssignmentsDTOs
        );
    }

    @Override
    public SkillProfileDTO putSkillProfileById(UUID skillProfileId, SkillProfileDTO skillProfileDTO) {

        List<IndustryBusinessObject> industries = skillProfileDTO.getIndustryKnowledges();
        industryKnowledgeService.saveIndustryKnowledges(industries, skillProfileId);

        List<QualificationBusinessObject> qualifications = skillProfileDTO.getQualifications();
        qualificationService.saveAll(skillProfileId, qualifications);


        List<LanguageKnowledgeBusinessObject> languageKnowledges = skillProfileDTO.getLanguages();
        languageKnowledgeService.saveAll(skillProfileId, languageKnowledges);

        return skillProfileDTO;
    }
}
