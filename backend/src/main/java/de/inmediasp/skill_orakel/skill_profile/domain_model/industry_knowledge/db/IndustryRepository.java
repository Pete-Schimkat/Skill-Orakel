package de.inmediasp.skill_orakel.skill_profile.domain_model.industry_knowledge.db;

import de.inmediasp.skill_orakel.skill_profile.domain_model.industry_knowledge.db.entity.Industry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IndustryRepository extends JpaRepository<Industry, UUID> {
}
