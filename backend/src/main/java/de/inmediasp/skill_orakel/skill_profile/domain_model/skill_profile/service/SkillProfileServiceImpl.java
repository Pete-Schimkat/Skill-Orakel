package de.inmediasp.skill_orakel.skill_profile.domain_model.skill_profile.service;

import de.inmediasp.skill_orakel.skill_profile.domain_model.skill_profile.SkillProfileService;
import de.inmediasp.skill_orakel.skill_profile.domain_model.skill_profile.db.SkillProfileRepository;
import de.inmediasp.skill_orakel.skill_profile.domain_model.skill_profile.db.entity.SkillProfile;
import de.inmediasp.skill_orakel.skill_profile.domain_model.skill_profile.model.SkillProfileBusinessObject;
import de.inmediasp.skill_orakel.skill_profile.services.get_skill_profile.mappers.EntityToBusinessObjectMapper;
import de.inmediasp.skill_orakel.skill_profile.web.exception_handling.exception.DatabaseAccessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SkillProfileServiceImpl implements SkillProfileService {

    private final SkillProfileRepository repository;
    private final EntityToBusinessObjectMapper<SkillProfileBusinessObject, SkillProfile> mapper;

    @Autowired
    public SkillProfileServiceImpl(SkillProfileRepository repository,
            EntityToBusinessObjectMapper<SkillProfileBusinessObject, SkillProfile> mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public SkillProfileBusinessObject findById(UUID id) {

        try {
            return repository.findById(id)
                    .map(mapper::convertToBusinessObject)
                    .orElse(null);
        } catch (DataAccessException exception){
            throw new DatabaseAccessException("Could not connect to the database");
        }
    }
}
