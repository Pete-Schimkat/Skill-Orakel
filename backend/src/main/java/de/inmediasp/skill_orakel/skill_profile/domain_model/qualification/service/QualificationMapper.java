package de.inmediasp.skill_orakel.skill_profile.domain_model.qualification.service;

import de.inmediasp.skill_orakel.skill_profile.domain_model.qualification.db.entity.Qualification;
import de.inmediasp.skill_orakel.skill_profile.domain_model.qualification.model.QualificationBusinessObject;
import de.inmediasp.skill_orakel.skill_profile.services.get_skill_profile.mappers.EntitiesToBusinessObjectsMapper;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class QualificationMapper implements EntitiesToBusinessObjectsMapper<QualificationBusinessObject, Qualification> {

    @Override
    public List<QualificationBusinessObject> convertToBusinessObjects(List<Qualification> entities) {
        return entities.stream().map(this::convertToBusinessObject).toList();
    }

    private QualificationBusinessObject convertToBusinessObject(Qualification qualification) {
        if (qualification == null) {
            return null;
        }

        return new QualificationBusinessObject(qualification.getId(), 
            qualification.getTitle(), 
            qualification.getIssuer(), 
            qualification.getDescription(), 
            qualification.getDate());
    }

    @Override
    public List<Qualification> convertToEntities(List<QualificationBusinessObject> businessObjects,
            UUID skillProfileId) {
        return businessObjects.stream()
                .map(qualification -> convertToEntity(skillProfileId, qualification))
                .toList();
    }

    private Qualification convertToEntity(UUID skillProfileId, QualificationBusinessObject qualificationBusinessObject) {
        return new Qualification(qualificationBusinessObject.getId(),
                skillProfileId,
                qualificationBusinessObject.getTitle(),
                qualificationBusinessObject.getIssuer(),
                qualificationBusinessObject.getDescription(),
                qualificationBusinessObject.getDate()
        );
    }
}
