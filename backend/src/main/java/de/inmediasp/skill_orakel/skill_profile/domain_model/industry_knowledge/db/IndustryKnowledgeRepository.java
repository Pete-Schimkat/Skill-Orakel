package de.inmediasp.skill_orakel.skill_profile.domain_model.industry_knowledge.db;

import org.springframework.data.jpa.repository.JpaRepository;

import de.inmediasp.skill_orakel.skill_profile.domain_model.industry_knowledge.db.entity.IndustryKnowledge;
import de.inmediasp.skill_orakel.skill_profile.domain_model.industry_knowledge.db.entity.IndustryKnowledgeId;

import java.util.UUID;
import java.util.List;


public interface IndustryKnowledgeRepository extends JpaRepository<IndustryKnowledge, IndustryKnowledgeId> {
    List<IndustryKnowledge> findAllByIndustryKnowledgeId_SkillProfileId(UUID skillProfileId);
    // void deleteAll(List<IndustryKnowledge> industries);
    // void saveAll(List<IndustryKnowledge> industries);
    void deleteAllByIndustryKnowledgeId_SkillProfileId(UUID skillProfileId);
}
