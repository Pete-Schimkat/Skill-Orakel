package de.inmediasp.skill_orakel.skill_profile.services.domain_model.language_knowledge.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import de.inmediasp.skill_orakel.skill_profile.domain_model.language_knowledge.LanguageKnowledgeService;
import de.inmediasp.skill_orakel.skill_profile.domain_model.language_knowledge.db.LanguageKnowledgeRepository;
import de.inmediasp.skill_orakel.skill_profile.domain_model.language_knowledge.db.LanguageRepository;
import de.inmediasp.skill_orakel.skill_profile.domain_model.language_knowledge.db.entity.Language;
import de.inmediasp.skill_orakel.skill_profile.domain_model.language_knowledge.db.entity.LanguageKnowledge;
import de.inmediasp.skill_orakel.skill_profile.domain_model.language_knowledge.model.LanguageBusinessObject;
import de.inmediasp.skill_orakel.skill_profile.domain_model.language_knowledge.model.LanguageKnowledgeBusinessObject;
import de.inmediasp.skill_orakel.skill_profile.domain_model.language_knowledge.service.LanguageKnowledgeMapper;
import de.inmediasp.skill_orakel.skill_profile.domain_model.language_knowledge.service.LanguageKnowledgeServiceImpl;
import de.inmediasp.skill_orakel.skill_profile.domain_model.language_knowledge.service.LanguageMapper;
import de.inmediasp.skill_orakel.skill_profile.services.get_skill_profile.mappers.EntitiesToBusinessObjectsMapper;

class LanguageKnowledgeServiceImplTest {

    private final LanguageKnowledgeRepository mockRepository = mock(LanguageKnowledgeRepository.class);
    private final EntitiesToBusinessObjectsMapper<LanguageKnowledgeBusinessObject, LanguageKnowledge> mapper
            = mock(LanguageKnowledgeMapper.class);

    private final EntitiesToBusinessObjectsMapper<LanguageBusinessObject, Language> languageMapper = mock(LanguageMapper.class);
    private final LanguageRepository languageRepo = mock(LanguageRepository.class);
   
    private final LanguageKnowledgeService languageService =
            new LanguageKnowledgeServiceImpl(mockRepository, mapper, languageMapper, languageRepo);

    @Test
    void GIVEN_db_has_entries_with_skillProfileId_THEN_findBySkillProfileId_RETURNS_List_of_elements() {

        List<LanguageKnowledge> languageKnowledges = List.of(new LanguageKnowledge(), new LanguageKnowledge());
        List<LanguageKnowledgeBusinessObject> languageKnowledgesbusinessObjects
                = List.of(new LanguageKnowledgeBusinessObject(), new LanguageKnowledgeBusinessObject());

        UUID id = UUID.randomUUID();
        when(mockRepository.findAllByLanguageKnowledgeId_SkillProfileId(id)).thenReturn(languageKnowledges);
        when(mapper.convertToBusinessObjects(languageKnowledges)).thenReturn(languageKnowledgesbusinessObjects);

        List<LanguageKnowledgeBusinessObject> result = languageService.findBySkillProfileId(id);

        assertEquals(languageKnowledgesbusinessObjects, result);
    }

    @Test
    void GIVEN_db_has_NO_entries_with_skillProfileId_THEN_findBySkillProfileId_RETURNS_empty_list() {

        List<LanguageKnowledge> languageKnowledges = new ArrayList<>();
        List<LanguageKnowledgeBusinessObject> languageKnowledgesbusinessObjects = new ArrayList<>();

        UUID id = UUID.randomUUID();
        when(mockRepository.findAllByLanguageKnowledgeId_SkillProfileId(id)).thenReturn(languageKnowledges);
        when(mapper.convertToBusinessObjects(languageKnowledges)).thenReturn(languageKnowledgesbusinessObjects);

        List<LanguageKnowledgeBusinessObject> result = languageService.findBySkillProfileId(id);

        assertTrue(result.isEmpty());
    }

}