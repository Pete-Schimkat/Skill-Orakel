package de.inmediasp.skill_orakel.skill_profile.domain_model.qualification;

import de.inmediasp.skill_orakel.skill_profile.domain_model.qualification.model.QualificationBusinessObject;

import java.util.List;
import java.util.UUID;

public interface QualificationService {
    List<QualificationBusinessObject> findAllBySkillProfileId(UUID id);
    List<QualificationBusinessObject> saveAll(UUID skillProfileId, List<QualificationBusinessObject> qualificationBusinessObjects);
}
