package de.inmediasp.skill_orakel.skill_profile.services.get_skill_profile.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import de.inmediasp.skill_orakel.skill_profile.domain_model.basic_info.model.BasicInfoBusinessObject;
import de.inmediasp.skill_orakel.skill_profile.domain_model.industry_knowledge.model.IndustryBusinessObject;
import de.inmediasp.skill_orakel.skill_profile.domain_model.language_knowledge.model.LanguageKnowledgeBusinessObject;
import de.inmediasp.skill_orakel.skill_profile.domain_model.project_assignment.model.ProjectAssignmentBusinessObject;
import de.inmediasp.skill_orakel.skill_profile.domain_model.qualification.model.QualificationBusinessObject;
import de.inmediasp.skill_orakel.skill_profile.domain_model.skill.model.SkillBusinessObject;
import de.inmediasp.skill_orakel.skill_profile.domain_model.work_experience.model.WorkExperienceBusinessObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SkillProfileDTO {
    @JsonProperty(required = true)
    private UUID skillProfileId;

    @JsonProperty(required = true)
    private BasicInfoBusinessObject basicInfo;

    @JsonProperty(required = true)
    private List<QualificationBusinessObject> qualifications;

    @JsonProperty(required = true)
    private List<IndustryBusinessObject> industryKnowledges;

    @JsonProperty(required = true)
    private List<LanguageKnowledgeBusinessObject> languages;

    @JsonProperty(required = true)
    private List<SkillBusinessObject> skills;

    @JsonProperty(required = true)
    private List<WorkExperienceBusinessObject> workExperience;

    @JsonProperty(required = true)
    private List<ProjectAssignmentBusinessObject> projectAssignments;
}
