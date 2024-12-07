package de.inmediasp.skill_orakel.skill_profile.domain_model.language_knowledge;

import de.inmediasp.skill_orakel.skill_profile.domain_model.language_knowledge.model.LanguageBusinessObject;
import de.inmediasp.skill_orakel.skill_profile.domain_model.language_knowledge.model.LanguageKnowledgeBusinessObject;

import java.util.List;
import java.util.UUID;

public interface LanguageService {

    List<LanguageBusinessObject> saveAllLanguages(List<LanguageKnowledgeBusinessObject> languageKnowledgeBusinessObjects, UUID skillProfileId);
}
