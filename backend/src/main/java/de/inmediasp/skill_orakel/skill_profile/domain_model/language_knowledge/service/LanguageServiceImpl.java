package de.inmediasp.skill_orakel.skill_profile.domain_model.language_knowledge.service;

import de.inmediasp.skill_orakel.skill_profile.domain_model.language_knowledge.LanguageService;
import de.inmediasp.skill_orakel.skill_profile.domain_model.language_knowledge.db.LanguageRepository;
import de.inmediasp.skill_orakel.skill_profile.domain_model.language_knowledge.db.entity.Language;
import de.inmediasp.skill_orakel.skill_profile.domain_model.language_knowledge.model.LanguageBusinessObject;
import de.inmediasp.skill_orakel.skill_profile.domain_model.language_knowledge.model.LanguageKnowledgeBusinessObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class LanguageServiceImpl implements LanguageService {

    private final LanguageRepository languageRepository;
    private final LanguageMapper languageMapper;

    public LanguageServiceImpl(LanguageRepository languageRepository, LanguageMapper languageMapper) {
        this.languageRepository = languageRepository;
        this.languageMapper = languageMapper;
    }

    @Override
    public List<LanguageBusinessObject> saveAllLanguages(List<LanguageKnowledgeBusinessObject> languageKnowledgeBusinessObjects, UUID skillProfileId) {

        List<LanguageBusinessObject> languageBusinessObjects =  languageKnowledgeBusinessObjects.stream()
                .map(languageKnowledgeBusinessObject -> new LanguageBusinessObject(
                        languageKnowledgeBusinessObject.getLanguageId(), languageKnowledgeBusinessObject.getLanguageName())
                ).toList();

        List<Language> languages = languageMapper.convertToEntities(languageBusinessObjects, skillProfileId);
        List<Language> savedLanguages = languageRepository.saveAll(languages);

        return languageMapper.convertToBusinessObjects(savedLanguages);
    }
}
