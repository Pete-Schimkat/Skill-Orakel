package de.inmediasp.skill_orakel.skill_profile.domain_model.industry_knowledge.service;

import de.inmediasp.skill_orakel.skill_profile.domain_model.industry_knowledge.IndustryService;
import de.inmediasp.skill_orakel.skill_profile.domain_model.industry_knowledge.db.IndustryRepository;
import de.inmediasp.skill_orakel.skill_profile.domain_model.industry_knowledge.db.entity.Industry;
import de.inmediasp.skill_orakel.skill_profile.domain_model.industry_knowledge.model.IndustryBusinessObject;
import de.inmediasp.skill_orakel.skill_profile.services.get_skill_profile.mappers.EntitiesToBusinessObjectsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IndustryServiceImpl implements IndustryService {

    private final IndustryRepository repository;
    private final EntitiesToBusinessObjectsMapper<IndustryBusinessObject, Industry> mapper;

    @Autowired
    public IndustryServiceImpl(IndustryRepository repository, EntitiesToBusinessObjectsMapper<IndustryBusinessObject, Industry> mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<IndustryBusinessObject> getAllIndustries() {
        return mapper.convertToBusinessObjects(repository.findAll());
    }
}
