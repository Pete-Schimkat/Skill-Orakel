package de.inmediasp.skill_orakel.skill_profile.domain_model.industry_knowledge;

import java.util.List;
import java.util.UUID;

import de.inmediasp.skill_orakel.skill_profile.domain_model.industry_knowledge.model.IndustryBusinessObject;

public interface IndustryKnowledgeService {
    List<IndustryBusinessObject> findBySkillProfileId(UUID skillProfileId);
    List<IndustryBusinessObject> saveIndustryKnowledges(List<IndustryBusinessObject> industries, UUID skillprofileId);
}
