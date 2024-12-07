package de.inmediasp.skill_orakel.skill_profile.domain_model.language_knowledge.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.inmediasp.skill_orakel.skill_profile.domain_model.language_knowledge.LanguageKnowledgeService;
import de.inmediasp.skill_orakel.skill_profile.domain_model.language_knowledge.db.LanguageKnowledgeRepository;
import de.inmediasp.skill_orakel.skill_profile.domain_model.language_knowledge.db.LanguageRepository;
import de.inmediasp.skill_orakel.skill_profile.domain_model.language_knowledge.db.entity.Language;
import de.inmediasp.skill_orakel.skill_profile.domain_model.language_knowledge.db.entity.LanguageKnowledge;
import de.inmediasp.skill_orakel.skill_profile.domain_model.language_knowledge.db.entity.LanguageKnowledgeId;
import de.inmediasp.skill_orakel.skill_profile.domain_model.language_knowledge.model.LanguageBusinessObject;
import de.inmediasp.skill_orakel.skill_profile.domain_model.language_knowledge.model.LanguageKnowledgeBusinessObject;
import de.inmediasp.skill_orakel.skill_profile.services.get_skill_profile.mappers.EntitiesToBusinessObjectsMapper;

@Service
@Transactional
public class LanguageKnowledgeServiceImpl implements LanguageKnowledgeService {

    private final LanguageKnowledgeRepository repository;
    private final EntitiesToBusinessObjectsMapper<LanguageKnowledgeBusinessObject, LanguageKnowledge> mapper;
    private final EntitiesToBusinessObjectsMapper<LanguageBusinessObject, Language> languageMapper;
    private final LanguageRepository languageRepository;

    @Autowired
    public LanguageKnowledgeServiceImpl(LanguageKnowledgeRepository repository, 
             EntitiesToBusinessObjectsMapper<LanguageKnowledgeBusinessObject,
                     LanguageKnowledge> mapper,
                     EntitiesToBusinessObjectsMapper<LanguageBusinessObject, Language> languageMapper,
                     LanguageRepository languageRepository) {
        this.repository = repository;
        this.mapper = mapper;
        this.languageMapper = languageMapper;
        this.languageRepository = languageRepository;
    }

    @Override
    public List<LanguageKnowledgeBusinessObject> findBySkillProfileId(UUID skillProfileId) {
        List<LanguageKnowledge> languageKnowledges = repository.findAllByLanguageKnowledgeId_SkillProfileId(skillProfileId);

        return mapper.convertToBusinessObjects(languageKnowledges);         
    }

    @Override
    public List<LanguageKnowledgeBusinessObject> saveAll(UUID skillProfileId, List<LanguageKnowledgeBusinessObject> languageKnowledgeBusinessObjects) {

        repository.deleteAllByLanguageKnowledgeId_SkillProfileId(skillProfileId);

        List<LanguageKnowledge> languageKnowledges = mapper.convertToEntities(languageKnowledgeBusinessObjects, skillProfileId);
       

        for(LanguageKnowledge languageKnowledge : languageKnowledges){

            Language language = languageKnowledge.getLanguage();

            Language savedLanguage = languageRepository.save(language);

            languageKnowledge.setLanguage(savedLanguage); 
            languageKnowledge.setLanguageKnowledgeId(new LanguageKnowledgeId(skillProfileId, savedLanguage.getLanguageId()));
        }

        List<LanguageKnowledge> savedLanguageKnowledges = repository.saveAll(languageKnowledges);
        return mapper.convertToBusinessObjects(savedLanguageKnowledges);
    }
}
