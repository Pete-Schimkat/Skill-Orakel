package de.inmediasp.skill_orakel.skill_profile.domain_model.industry_knowledge.service;

import de.inmediasp.skill_orakel.skill_profile.domain_model.industry_knowledge.db.entity.Industry;
import de.inmediasp.skill_orakel.skill_profile.domain_model.industry_knowledge.db.entity.IndustryKnowledge;
import de.inmediasp.skill_orakel.skill_profile.domain_model.industry_knowledge.db.entity.IndustryKnowledgeId;
import de.inmediasp.skill_orakel.skill_profile.domain_model.industry_knowledge.model.IndustryBusinessObject;
import de.inmediasp.skill_orakel.skill_profile.services.get_skill_profile.mappers.EntitiesToBusinessObjectsMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class IndustryKnowledgeMapper 
    implements EntitiesToBusinessObjectsMapper<IndustryBusinessObject, IndustryKnowledge> {

    @Override
    public List<IndustryBusinessObject> convertToBusinessObjects(List<IndustryKnowledge> entities) {
        return entities.stream()
            .map(this::convertToBusinessObject)
            .toList();
    }

    
    private IndustryBusinessObject convertToBusinessObject(IndustryKnowledge industryKnowledge) {
        if (industryKnowledge == null) {
            return null;
        }

        return new IndustryBusinessObject(industryKnowledge.getIndustry().getId(), industryKnowledge.getIndustry().getName());
    }

    @Override
    public List<IndustryKnowledge> convertToEntities(List<IndustryBusinessObject> industries, UUID skillProfileId){

        return industries.stream()
            .map(industry -> convertToEntity(industry, skillProfileId))
            .toList();
    }

    private IndustryKnowledge convertToEntity(IndustryBusinessObject industry, UUID skillProfileId){

        IndustryKnowledgeId id = new IndustryKnowledgeId(skillProfileId, industry.getId());
       
        Industry industryEntity = new Industry();
        industryEntity.setId(industry.getId());
        industryEntity.setName(industry.getName());

        IndustryKnowledge industryKnowledge = new IndustryKnowledge();
        industryKnowledge.setIndustryKnowledgeId(id);
        industryKnowledge.setIndustry(industryEntity);

        return industryKnowledge;
    }
}