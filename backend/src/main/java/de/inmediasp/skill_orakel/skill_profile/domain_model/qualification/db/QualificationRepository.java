package de.inmediasp.skill_orakel.skill_profile.domain_model.qualification.db;

import de.inmediasp.skill_orakel.skill_profile.domain_model.qualification.db.entity.Qualification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface QualificationRepository extends JpaRepository<Qualification, UUID> {
    List<Qualification> findAllBySkillProfileId(UUID skillProfileId);
    List<Qualification> deleteAllBySkillProfileId(UUID skillProfileId);
}
