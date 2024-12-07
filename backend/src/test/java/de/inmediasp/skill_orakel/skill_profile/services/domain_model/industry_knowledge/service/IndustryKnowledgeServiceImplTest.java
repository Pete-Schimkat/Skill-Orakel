package de.inmediasp.skill_orakel.skill_profile.services.domain_model.industry_knowledge.service;

import de.inmediasp.skill_orakel.skill_profile.domain_model.industry_knowledge.IndustryKnowledgeService;
import de.inmediasp.skill_orakel.skill_profile.domain_model.industry_knowledge.db.IndustryKnowledgeRepository;
import de.inmediasp.skill_orakel.skill_profile.domain_model.industry_knowledge.db.entity.Industry;
import de.inmediasp.skill_orakel.skill_profile.domain_model.industry_knowledge.db.entity.IndustryKnowledge;
import de.inmediasp.skill_orakel.skill_profile.domain_model.industry_knowledge.db.entity.IndustryKnowledgeId;
import de.inmediasp.skill_orakel.skill_profile.domain_model.industry_knowledge.model.IndustryBusinessObject;
import de.inmediasp.skill_orakel.skill_profile.domain_model.industry_knowledge.service.IndustryKnowledgeMapper;
import de.inmediasp.skill_orakel.skill_profile.domain_model.industry_knowledge.service.IndustryKnowledgeServiceImpl;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class IndustryKnowledgeServiceImplTest {

    private final IndustryKnowledgeRepository repository = mock(IndustryKnowledgeRepository.class);
    private final IndustryKnowledgeMapper mapper = mock(IndustryKnowledgeMapper.class);
    private final IndustryKnowledgeService industryKnowledgeService = new IndustryKnowledgeServiceImpl(repository, mapper);

    @Test
    void GIVEN_dbHasEntryWithSkillProfileId_THEN_findByIdSkillProfileId_RETURNS_thoseElement() {

        UUID id = UUID.randomUUID();

        List<IndustryKnowledge> industries= List.of(new IndustryKnowledge(), new IndustryKnowledge());
        List<IndustryBusinessObject> industryBusinessObjects = List.of(new IndustryBusinessObject(), new IndustryBusinessObject());

        when(repository.findAllByIndustryKnowledgeId_SkillProfileId(id)).thenReturn(industries);
        when(mapper.convertToBusinessObjects(industries)).thenReturn(industryBusinessObjects);

        List<IndustryBusinessObject> result = industryKnowledgeService.findBySkillProfileId(id);

        assertEquals(industryBusinessObjects, result);
    }

    @Test
    void GIVEN_dbHasNoEntryWithSkillProfileId_THEN_findBySkillProfileId_RETURNS_emptyList() {

        UUID id = UUID.randomUUID();

        List<IndustryKnowledge> industries= new ArrayList<>();
        List<IndustryBusinessObject> industryBusinessObjects = new ArrayList<>();

        when(repository.findAllByIndustryKnowledgeId_SkillProfileId(id)).thenReturn(industries);
        when(mapper.convertToBusinessObjects(industries)).thenReturn(industryBusinessObjects);

        List<IndustryBusinessObject> result = industryKnowledgeService.findBySkillProfileId(id);

        assertTrue(result.isEmpty());
    }

    @Test
    void GIVEN_THEN_SUCCESS() {
        List<IndustryBusinessObject> newIndustries = List.of(new IndustryBusinessObject(UUID.randomUUID(), "Automotive"), new IndustryBusinessObject(UUID.randomUUID(), "Publishing"));

        UUID id = UUID.randomUUID();

        Industry industry = new Industry(UUID.randomUUID(), "Automotive");        
        IndustryKnowledge knowledge = new IndustryKnowledge(new IndustryKnowledgeId(id, industry.getId()), industry);

        when(mapper.convertToEntities(newIndustries, id)).thenReturn(List.of(knowledge));

        List<IndustryBusinessObject> result = industryKnowledgeService.saveIndustryKnowledges(newIndustries, id);

        //assertEquals(knowledge, result);
    }
}