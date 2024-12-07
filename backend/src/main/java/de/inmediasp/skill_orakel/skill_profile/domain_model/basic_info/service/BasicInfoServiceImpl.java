package de.inmediasp.skill_orakel.skill_profile.domain_model.basic_info.service;

import de.inmediasp.skill_orakel.skill_profile.domain_model.basic_info.BasicInfoService;
import de.inmediasp.skill_orakel.skill_profile.domain_model.basic_info.db.BasicInfoRepository;
import de.inmediasp.skill_orakel.skill_profile.domain_model.basic_info.db.entity.BasicInfo;
import de.inmediasp.skill_orakel.skill_profile.domain_model.basic_info.model.BasicInfoBusinessObject;
import de.inmediasp.skill_orakel.skill_profile.services.get_skill_profile.mappers.EntityToBusinessObjectMapper;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BasicInfoServiceImpl implements BasicInfoService {

    private final BasicInfoRepository repository;
    private final EntityToBusinessObjectMapper<BasicInfoBusinessObject, BasicInfo> mapper;

    public BasicInfoServiceImpl(BasicInfoRepository repository,
                                EntityToBusinessObjectMapper<BasicInfoBusinessObject, BasicInfo> mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public BasicInfoBusinessObject findById(UUID id) {
        return repository.findById(id)
                .map(mapper::convertToBusinessObject)
                .orElse(null);
    }
}
