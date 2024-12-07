package de.inmediasp.skill_orakel.skill_profile.domain_model.language_knowledge;

import de.inmediasp.skill_orakel.skill_profile.domain_model.language_knowledge.model.LanguageKnowledgeBusinessObject;

import java.util.List;
import java.util.UUID;

public interface LanguageKnowledgeService {

    List<LanguageKnowledgeBusinessObject> findBySkillProfileId(UUID skillProfileId);
    List<LanguageKnowledgeBusinessObject> saveAll(UUID skillProfileId, List<LanguageKnowledgeBusinessObject> languageKnowledgeBusinessObjects);
}
