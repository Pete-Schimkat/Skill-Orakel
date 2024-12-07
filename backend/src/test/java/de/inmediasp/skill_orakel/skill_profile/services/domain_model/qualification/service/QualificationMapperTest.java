package de.inmediasp.skill_orakel.skill_profile.services.domain_model.qualification.service;

import de.inmediasp.skill_orakel.skill_profile.domain_model.qualification.db.entity.Qualification;
import de.inmediasp.skill_orakel.skill_profile.domain_model.qualification.model.QualificationBusinessObject;
import de.inmediasp.skill_orakel.skill_profile.domain_model.qualification.service.QualificationMapper;
import de.inmediasp.skill_orakel.skill_profile.services.get_skill_profile.mappers.EntitiesToBusinessObjectsMapper;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class QualificationMapperTest {

    private final EntitiesToBusinessObjectsMapper<QualificationBusinessObject, Qualification> mapper
            = new QualificationMapper();

    @Test
    void GIVEN_listWithProjectAssignment_THEN_convertToBusinessObjects_Returns_correctlyMappedList() {

        UUID id = UUID.randomUUID();
        String issuer = "Liquibase University";
        String description = "a skill";
        String title = "official Expert of all the sciences";
        LocalDate date = LocalDate.of(1871, 1, 18);

        QualificationBusinessObject qualificationBusinessObject = new QualificationBusinessObject();
        qualificationBusinessObject.setIssuer(issuer);
        qualificationBusinessObject.setId(id);
        qualificationBusinessObject.setTitle(title);
        qualificationBusinessObject.setDescription(description);
        qualificationBusinessObject.setDate(date);

        List<QualificationBusinessObject> qualificationBusinessObjects = List.of(qualificationBusinessObject);

        Qualification qualification = new Qualification();
        qualification.setDate(date);
        qualification.setDescription(description);
        qualification.setTitle(title);
        qualification.setIssuer(issuer);
        qualification.setId(id);

        List<Qualification> qualifications = List.of(qualification);

        List<QualificationBusinessObject> result = mapper.convertToBusinessObjects(qualifications);

        assertEquals(qualificationBusinessObjects, result);
    }

    @Test
    void GIVEN_emptyList_THEN_convertToBusinessObject_RETURNS_emptyList() {

        List<Qualification> qualifications = new ArrayList<>();

        List<QualificationBusinessObject> result = mapper.convertToBusinessObjects(qualifications);

        assertTrue(result.isEmpty());
    }

    @Test
    void GIVEN_ListContainsNull_THEN_convertToBusinessObjects_RETURNS_ListWithNull() {

        List<Qualification> qualifications = new ArrayList<>();
        qualifications.add(null);

        List<QualificationBusinessObject> result = mapper.convertToBusinessObjects(qualifications);

        assertTrue(result.contains(null));
    }

}