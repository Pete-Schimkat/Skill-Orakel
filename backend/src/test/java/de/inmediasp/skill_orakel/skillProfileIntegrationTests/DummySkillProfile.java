package de.inmediasp.skill_orakel.skillProfileIntegrationTests;

import de.inmediasp.skill_orakel.skill_profile.domain_model.basic_info.model.BasicInfoBusinessObject;
import de.inmediasp.skill_orakel.skill_profile.domain_model.industry_knowledge.model.IndustryBusinessObject;
import de.inmediasp.skill_orakel.skill_profile.domain_model.language_knowledge.db.entity.LanguageLevel;
import de.inmediasp.skill_orakel.skill_profile.domain_model.language_knowledge.model.LanguageKnowledgeBusinessObject;
import de.inmediasp.skill_orakel.skill_profile.domain_model.project_assignment.model.ProjectAssignmentBusinessObject;
import de.inmediasp.skill_orakel.skill_profile.domain_model.qualification.model.QualificationBusinessObject;
import de.inmediasp.skill_orakel.skill_profile.domain_model.skill.model.SkillBusinessObject;
import de.inmediasp.skill_orakel.skill_profile.domain_model.skill_profile.model.SkillProfileBusinessObject;
import de.inmediasp.skill_orakel.skill_profile.domain_model.work_experience.model.WorkExperienceBusinessObject;
import de.inmediasp.skill_orakel.skill_profile.services.get_skill_profile.dto.SkillProfileDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class DummySkillProfile {
    public DummySkillProfile() {
    }

    protected SkillProfileDTO getSkillProfileDTO() {
        BasicInfoBusinessObject basicInfo = new BasicInfoBusinessObject();
        basicInfo.setId(UUID.fromString("e1750f44-c689-46a6-826b-56fd3efd0ed8"));
        basicInfo.setFirstName("Heinz");
        basicInfo.setLastName("Testmeister");

        SkillProfileBusinessObject skillProfile = new SkillProfileBusinessObject();
        skillProfile.setId(UUID.fromString("9483a127-d4ba-4f97-b5da-eec97c86036b"));

        QualificationBusinessObject qualifications = new QualificationBusinessObject();
        qualifications.setId(UUID.fromString("ff138791-fddc-4513-89b3-8d752155cd0b"));
        qualifications.setTitle("Offical Java Syntactical Sugar Connaisseur");
        qualifications.setIssuer("Java Island's Java Foundation");
        qualifications.setDescription("Distinguish all the java beans from regular brews");
        qualifications.setDate(LocalDate.of(2022, 6, 2));

        IndustryBusinessObject industry = new IndustryBusinessObject();
        industry.setId(UUID.fromString("0ab136c6-9019-4e92-965a-0c3110d05918"));
        industry.setName("Luft- und Raumfahrtindustrie");

        LanguageKnowledgeBusinessObject languageKnowledge = new LanguageKnowledgeBusinessObject();
        languageKnowledge.setLanguageId(UUID.fromString("8ea2cc35-bc9e-4eb1-ad72-b288a5b2bbfe"));
        languageKnowledge.setLanguageName("Deutsch");
        languageKnowledge.setLanguageLevel(LanguageLevel.VERHANDLUNGSSICHER);

        WorkExperienceBusinessObject workExperience = new WorkExperienceBusinessObject();
        workExperience.setId(UUID.fromString("03062495-c6c3-40d6-867b-60450c19cf15"));
        workExperience.setEmployer("Dataciders InMediasP");
        workExperience.setDescription("Matse");
        workExperience.setStartDate(LocalDate.of(2023, 10, 7));
        workExperience.setEndDate(LocalDate.of(2020, 10, 7));

        SkillBusinessObject skill = new SkillBusinessObject();
        skill.setId(UUID.fromString("73aabba3-287b-403e-875d-2aad15e0945a"));
        skill.setName("More time to drink coffee");

        ProjectAssignmentBusinessObject projectAssignment = new ProjectAssignmentBusinessObject();
        projectAssignment.setProjectId(UUID.fromString("6c325811-02bd-4f57-a267-29defcb43527"));
        projectAssignment.setStartDate(LocalDate.of(2024,10,10));
        projectAssignment.setEndDate(LocalDate.of(2024,10,31));
        projectAssignment.setSkills(List.of(skill));
        projectAssignment.setResponsibilities("In charge of untangling legacy spaghetti code");
        projectAssignment.setName("Skillorakel");
        projectAssignment.setDescription("Drei Idioten und ein Backend");
        projectAssignment.setCustomer("wir selbst");

        List<IndustryBusinessObject> allIndustries = List.of(industry);
        List<QualificationBusinessObject> allQualificationDTOs = List.of(qualifications);
        List<LanguageKnowledgeBusinessObject> languageKnowledgeDTOs = List.of(languageKnowledge);
        List<SkillBusinessObject> skills = List.of(skill);
        List<WorkExperienceBusinessObject> allExperienceDTOs = List.of(workExperience);
        List<ProjectAssignmentBusinessObject> allAssignments = List.of(projectAssignment);

        // --> initialize expected DTO
        SkillProfileDTO expectedResult = new SkillProfileDTO();
        expectedResult.setSkillProfileId(UUID.fromString("9483a127-d4ba-4f97-b5da-eec97c86036b"));
        expectedResult.setBasicInfo(basicInfo);
        expectedResult.setQualifications(allQualificationDTOs);
        expectedResult.setIndustryKnowledges(allIndustries);
        expectedResult.setLanguages(languageKnowledgeDTOs);
        expectedResult.setSkills(skills);
        expectedResult.setWorkExperience(allExperienceDTOs);
        expectedResult.setProjectAssignments(allAssignments);
        return expectedResult;
    }
}