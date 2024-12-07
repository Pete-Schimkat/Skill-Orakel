package de.inmediasp.skill_orakel.skill_profile.services.domain_model.language_knowledge.service;

import de.inmediasp.skill_orakel.skill_profile.domain_model.language_knowledge.db.entity.Language;
import de.inmediasp.skill_orakel.skill_profile.domain_model.language_knowledge.db.entity.LanguageKnowledge;
import de.inmediasp.skill_orakel.skill_profile.domain_model.language_knowledge.db.entity.LanguageLevel;
import de.inmediasp.skill_orakel.skill_profile.domain_model.language_knowledge.model.LanguageKnowledgeBusinessObject;
import de.inmediasp.skill_orakel.skill_profile.domain_model.language_knowledge.service.LanguageKnowledgeMapper;
import de.inmediasp.skill_orakel.skill_profile.services.get_skill_profile.mappers.EntitiesToBusinessObjectsMapper;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LanguageKnowledgeMapperTest {

    private final EntitiesToBusinessObjectsMapper<LanguageKnowledgeBusinessObject, LanguageKnowledge> mapper
            = new LanguageKnowledgeMapper();

    @Test
    void GIVEN_listWithOneElement_THEN_elementIsMappedCorrectly() {

        UUID id = UUID.randomUUID();
        String languageName = "Sorbisch";

        Language language = new Language();
        language.setLanguageId(id);
        language.setLanguage(languageName);

        LanguageKnowledge languageKnowledge = new LanguageKnowledge();
        languageKnowledge.setLanguage(language);
        languageKnowledge.setLanguageLevel(LanguageLevel.ELEMENTARKENNTNISSE);

        LanguageKnowledgeBusinessObject languageKnowledgeBusinessObject
                = new LanguageKnowledgeBusinessObject(LanguageLevel.ELEMENTARKENNTNISSE, id, languageName);

        List<LanguageKnowledge> languageKnowledges = List.of(languageKnowledge);
        List<LanguageKnowledgeBusinessObject> languageKnowledgeBusinessObjects = List.of(languageKnowledgeBusinessObject);

        List<LanguageKnowledgeBusinessObject> result = mapper.convertToBusinessObjects(languageKnowledges);

        assertEquals(languageKnowledgeBusinessObjects, result);
    }

    @Test
    void GIVEN_emptyList_THEN_convertToBusinessObjects_RETUNRNS_emptyList() {

        List<LanguageKnowledge> languageKnowledges = new ArrayList<>();

        List<LanguageKnowledgeBusinessObject> result = mapper.convertToBusinessObjects(languageKnowledges);

        assertTrue(result.isEmpty());
    }

    @Test
    void GIVEN_ListContainsNull_THEN_outputListContainsNull() {

        List<LanguageKnowledge> languageKnowledges = new ArrayList<>();
        languageKnowledges.add(null);

        List<LanguageKnowledgeBusinessObject> result = mapper.convertToBusinessObjects(languageKnowledges);

        assertTrue(result.contains(null));
    }

}