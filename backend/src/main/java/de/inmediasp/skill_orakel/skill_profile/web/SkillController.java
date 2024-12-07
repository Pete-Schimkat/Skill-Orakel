package de.inmediasp.skill_orakel.skill_profile.web;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.inmediasp.skill_orakel.skill_profile.domain_model.skill.SkillService;
import de.inmediasp.skill_orakel.skill_profile.domain_model.skill.model.SkillBusinessObject;

@RestController
@RequestMapping("/api/skill")
public class SkillController {
    
    private final SkillService skillService;

    public SkillController(SkillService skillService){
        this.skillService = skillService;
    }

    @GetMapping
    public List<SkillBusinessObject> getSkills(){
        
        return skillService.getAllSkills();
    }
}
