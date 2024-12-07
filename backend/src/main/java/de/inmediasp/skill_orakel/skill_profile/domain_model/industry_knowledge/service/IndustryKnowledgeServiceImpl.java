package de.inmediasp.skill_orakel.skill_profile.domain_model.industry_knowledge.service;

import de.inmediasp.skill_orakel.skill_profile.domain_model.industry_knowledge.IndustryKnowledgeService;
import de.inmediasp.skill_orakel.skill_profile.domain_model.industry_knowledge.db.IndustryKnowledgeRepository;
import de.inmediasp.skill_orakel.skill_profile.domain_model.industry_knowledge.db.entity.IndustryKnowledge;
import de.inmediasp.skill_orakel.skill_profile.domain_model.industry_knowledge.model.IndustryBusinessObject;
import de.inmediasp.skill_orakel.skill_profile.services.get_skill_profile.mappers.EntitiesToBusinessObjectsMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class IndustryKnowledgeServiceImpl implements IndustryKnowledgeService {

    private final IndustryKnowledgeRepository repository;
    private final EntitiesToBusinessObjectsMapper<IndustryBusinessObject, IndustryKnowledge> mapper;

    @Autowired
    public IndustryKnowledgeServiceImpl(IndustryKnowledgeRepository repository,
            EntitiesToBusinessObjectsMapper<IndustryBusinessObject, IndustryKnowledge> mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<IndustryBusinessObject> findBySkillProfileId(UUID id) {
        List<IndustryKnowledge> industryKnowledges = repository.findAllByIndustryKnowledgeId_SkillProfileId(id);

        return mapper.convertToBusinessObjects(industryKnowledges);
    }

    @Override
    public List<IndustryBusinessObject> saveIndustryKnowledges(List<IndustryBusinessObject> newKnownIndustries, UUID skillProfileIdUuid) {

        List<IndustryKnowledge> industryEntities = mapper.convertToEntities(newKnownIndustries, skillProfileIdUuid);
        repository.deleteAllByIndustryKnowledgeId_SkillProfileId(skillProfileIdUuid);
        List<IndustryKnowledge> savedEntries = repository.saveAll(industryEntities);

        return mapper.convertToBusinessObjects(savedEntries);
    }
}
