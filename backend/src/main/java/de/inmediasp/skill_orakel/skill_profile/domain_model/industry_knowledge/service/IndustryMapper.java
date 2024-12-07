package de.inmediasp.skill_orakel.skill_profile.domain_model.industry_knowledge.service;

import de.inmediasp.skill_orakel.skill_profile.domain_model.industry_knowledge.db.entity.Industry;
import de.inmediasp.skill_orakel.skill_profile.domain_model.industry_knowledge.db.entity.IndustryKnowledgeId;
import de.inmediasp.skill_orakel.skill_profile.domain_model.industry_knowledge.model.IndustryBusinessObject;
import de.inmediasp.skill_orakel.skill_profile.services.get_skill_profile.mappers.EntitiesToBusinessObjectsMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class IndustryMapper
        implements EntitiesToBusinessObjectsMapper<IndustryBusinessObject, Industry> {

    @Override
    public List<IndustryBusinessObject> convertToBusinessObjects(List<Industry> entities) {
        return entities.stream()
                .map(this::convertToBusinessObject)
                .toList();
    }


    private IndustryBusinessObject convertToBusinessObject(Industry industry) {
        if (industry == null) {
            return null;
        }

        return new IndustryBusinessObject(industry.getId(), industry.getName());
    }

    @Override
    public List<Industry> convertToEntities(List<IndustryBusinessObject> industries, UUID skillProfileId){

        return industries.stream()
                .map(industry -> convertToEntity(industry, skillProfileId))
                .toList();
    }

    private Industry convertToEntity(IndustryBusinessObject industry, UUID skillProfileId){

        IndustryKnowledgeId id = new IndustryKnowledgeId(skillProfileId, industry.getId());

        Industry industryEntity = new Industry();
        industryEntity.setId(industry.getId());
        industryEntity.setName(industry.getName());

        return industryEntity;
    }
}