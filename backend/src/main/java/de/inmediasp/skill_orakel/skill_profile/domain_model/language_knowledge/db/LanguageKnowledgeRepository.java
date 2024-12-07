package de.inmediasp.skill_orakel.skill_profile.domain_model.language_knowledge.db;

import de.inmediasp.skill_orakel.skill_profile.domain_model.language_knowledge.db.entity.LanguageKnowledge;
import de.inmediasp.skill_orakel.skill_profile.domain_model.language_knowledge.db.entity.LanguageKnowledgeId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface LanguageKnowledgeRepository extends JpaRepository<LanguageKnowledge, LanguageKnowledgeId> {
    List<LanguageKnowledge> findAllByLanguageKnowledgeId_SkillProfileId(UUID skillProfileId);
    List<LanguageKnowledge> deleteAllByLanguageKnowledgeId_SkillProfileId(UUID skillProfileId);
}
