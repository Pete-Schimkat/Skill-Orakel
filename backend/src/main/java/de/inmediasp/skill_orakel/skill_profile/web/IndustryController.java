package de.inmediasp.skill_orakel.skill_profile.web;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.inmediasp.skill_orakel.skill_profile.domain_model.industry_knowledge.IndustryService;
import de.inmediasp.skill_orakel.skill_profile.domain_model.industry_knowledge.model.IndustryBusinessObject;

@RestController
@RequestMapping("/api/industry")
public class IndustryController {

    private final IndustryService industryService;

    public IndustryController(IndustryService industryService) {
        this.industryService = industryService;
    }

    @GetMapping
    public List<IndustryBusinessObject> getAllIndustries() {
        return industryService.getAllIndustries();
    }
}
