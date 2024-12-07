package de.inmediasp.skill_orakel.skill_profile.domain_model.language_knowledge.service;

import de.inmediasp.skill_orakel.skill_profile.domain_model.language_knowledge.db.entity.Language;
import de.inmediasp.skill_orakel.skill_profile.domain_model.language_knowledge.model.LanguageBusinessObject;
import de.inmediasp.skill_orakel.skill_profile.services.get_skill_profile.mappers.EntitiesToBusinessObjectsMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class LanguageMapper implements EntitiesToBusinessObjectsMapper<LanguageBusinessObject, Language> {

    @Override
    public List<LanguageBusinessObject> convertToBusinessObjects(List<Language> entities) {
        return entities.stream()
                .map(language -> new LanguageBusinessObject(
                        language.getLanguageId(), language.getLanguage())
                )
                .toList();
    }

    @Override
    public List<Language> convertToEntities(List<LanguageBusinessObject> businessObjects, UUID skillProfileId) {
        return businessObjects.stream()
                .map(languageKnowledgeBusinessObject -> new Language(
                        languageKnowledgeBusinessObject.getLanguageId(),
                        languageKnowledgeBusinessObject.getLanguageName())
                ).toList();
    }
}
