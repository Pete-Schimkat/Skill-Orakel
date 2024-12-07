package de.inmediasp.skill_orakel.skill_profile.services.domain_model.industry_knowledge.service;

import de.inmediasp.skill_orakel.skill_profile.domain_model.industry_knowledge.db.entity.Industry;
import de.inmediasp.skill_orakel.skill_profile.domain_model.industry_knowledge.db.entity.IndustryKnowledge;
import de.inmediasp.skill_orakel.skill_profile.domain_model.industry_knowledge.model.IndustryBusinessObject;
import de.inmediasp.skill_orakel.skill_profile.domain_model.industry_knowledge.service.IndustryKnowledgeMapper;
import de.inmediasp.skill_orakel.skill_profile.services.get_skill_profile.mappers.EntitiesToBusinessObjectsMapper;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IndustryKnowledgeMapperTest {

    private final EntitiesToBusinessObjectsMapper<IndustryBusinessObject, IndustryKnowledge> mapper
            = new IndustryKnowledgeMapper();
    @Test
    void GIVEN_listWithOneElement_THEN_elementIsMappedCorrectly() {

        UUID id = UUID.randomUUID();
        String industryName = "den Truthahn branchieren";

        IndustryKnowledge industryKnowledge = new IndustryKnowledge();
        Industry industry = new Industry();
        industry.setName(industryName);
        industry.setId(id);
        industryKnowledge.setIndustry(industry);

        IndustryBusinessObject industryBusinessObject = new IndustryBusinessObject();
        industryBusinessObject.setId(id);
        industryBusinessObject.setName(industryName);

        List<IndustryKnowledge> industryKnowledges = List.of(industryKnowledge);
        List<IndustryBusinessObject> industryBusinessObjects = List.of(industryBusinessObject);

        List<IndustryBusinessObject> result = mapper.convertToBusinessObjects(industryKnowledges);

        assertEquals(industryBusinessObjects, result);
    }

    @Test
    void GIVEN_emptyList_THEN_convertToBusinessObjects_RETUNRNS_emptyList() {

        List<IndustryKnowledge> industryKnowledges = new ArrayList<>();

        List<IndustryBusinessObject> result = mapper.convertToBusinessObjects(industryKnowledges);

        assertTrue(result.isEmpty());
    }

    @Test
    void GIVEN_EntityListContainsNull_THEN_convertToBusinessObjects_RETURNS_ListWithNull() {


        List<IndustryKnowledge> industryKnowledges = new ArrayList<>();
        industryKnowledges.add(null);
        List<IndustryBusinessObject> result = mapper.convertToBusinessObjects(industryKnowledges);

        assertTrue(result.contains(null));
    }
}