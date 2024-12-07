package de.inmediasp.skill_orakel.skill_profile.domain_model.language_knowledge.service;

import de.inmediasp.skill_orakel.skill_profile.domain_model.language_knowledge.db.entity.Language;
import de.inmediasp.skill_orakel.skill_profile.domain_model.language_knowledge.db.entity.LanguageKnowledge;
import de.inmediasp.skill_orakel.skill_profile.domain_model.language_knowledge.db.entity.LanguageKnowledgeId;
import de.inmediasp.skill_orakel.skill_profile.domain_model.language_knowledge.model.LanguageKnowledgeBusinessObject;
import de.inmediasp.skill_orakel.skill_profile.services.get_skill_profile.mappers.EntitiesToBusinessObjectsMapper;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class LanguageKnowledgeMapper
        implements EntitiesToBusinessObjectsMapper<LanguageKnowledgeBusinessObject, LanguageKnowledge> {

    @Override
    public List<LanguageKnowledgeBusinessObject> convertToBusinessObjects(List<LanguageKnowledge> entities) {
        return entities.stream()
                .map(this::convertToBusinessObject)
                .toList();
    }

    private LanguageKnowledgeBusinessObject convertToBusinessObject(LanguageKnowledge languageKnowledge) {
        if (languageKnowledge == null) {
            return null;
        }

        return new LanguageKnowledgeBusinessObject(
                languageKnowledge.getLanguageLevel(),
                languageKnowledge.getLanguage().getLanguageId(),
                languageKnowledge.getLanguage().getLanguage());
    }

    @Override
    public List<LanguageKnowledge> convertToEntities(List<LanguageKnowledgeBusinessObject> businessObjects,
            UUID skillProfileId) {
        return businessObjects.stream()
                .map(languageKnowledgeBusinessObject -> convertToEntity(languageKnowledgeBusinessObject, skillProfileId))
                .toList();
    }

    private LanguageKnowledge convertToEntity(LanguageKnowledgeBusinessObject languageKnowledgeBusinessObject, UUID skillProfileId) {
        return new LanguageKnowledge(
                new LanguageKnowledgeId(skillProfileId, languageKnowledgeBusinessObject.getLanguageId()),
                new Language(languageKnowledgeBusinessObject.getLanguageId(), languageKnowledgeBusinessObject.getLanguageName()),
                languageKnowledgeBusinessObject.getLanguageLevel()
        );
    }

}
