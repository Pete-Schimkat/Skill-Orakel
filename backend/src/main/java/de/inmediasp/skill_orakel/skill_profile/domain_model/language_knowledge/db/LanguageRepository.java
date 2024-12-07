package de.inmediasp.skill_orakel.skill_profile.domain_model.language_knowledge.db;

import de.inmediasp.skill_orakel.skill_profile.domain_model.language_knowledge.db.entity.Language;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LanguageRepository extends JpaRepository<Language, UUID> {
}
