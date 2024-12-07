package de.inmediasp.skill_orakel.skill_profile.domain_model.qualification.service;

import de.inmediasp.skill_orakel.skill_profile.domain_model.qualification.QualificationService;
import de.inmediasp.skill_orakel.skill_profile.domain_model.qualification.db.QualificationRepository;
import de.inmediasp.skill_orakel.skill_profile.domain_model.qualification.db.entity.Qualification;
import de.inmediasp.skill_orakel.skill_profile.domain_model.qualification.model.QualificationBusinessObject;
import de.inmediasp.skill_orakel.skill_profile.services.get_skill_profile.mappers.EntitiesToBusinessObjectsMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class QualificationServiceImpl implements QualificationService {

    private final QualificationRepository repository;
    private final EntitiesToBusinessObjectsMapper<QualificationBusinessObject, Qualification> mapper;

    public QualificationServiceImpl(QualificationRepository repository,
            EntitiesToBusinessObjectsMapper<QualificationBusinessObject, Qualification> mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<QualificationBusinessObject> findAllBySkillProfileId(UUID id) {
        List<Qualification> qualifications = repository.findAllBySkillProfileId(id);

        return mapper.convertToBusinessObjects(qualifications);
    }

    @Override
    public List<QualificationBusinessObject> saveAll(UUID skillProfileId, List<QualificationBusinessObject> qualificationBusinessObjects) {
        repository.deleteAllBySkillProfileId(skillProfileId);
        List<Qualification> newQualifications = mapper.convertToEntities(qualificationBusinessObjects, skillProfileId);
        return mapper.convertToBusinessObjects(repository.saveAll(newQualifications));
    }
}
