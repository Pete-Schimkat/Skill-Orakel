package de.inmediasp.skill_orakel.skill_profile.web;

import de.inmediasp.skill_orakel.skill_profile.services.SkillProfileRestService;
import de.inmediasp.skill_orakel.skill_profile.services.get_skill_profile.dto.SkillProfileDTO;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api")
public class SkillProfileController {

    private final SkillProfileRestService skillProfileRestService;

    public SkillProfileController(SkillProfileRestService skillProfileRestService) {
        this.skillProfileRestService = skillProfileRestService;
    }

    @Operation(summary = "Returns the skillProfile of the current user.")
    @GetMapping("/skillprofile/{skillProfileId}")
    public SkillProfileDTO getSkillProfile(@PathVariable UUID skillProfileId, Authentication authentication) {
        return skillProfileRestService.getSkillProfileById(skillProfileId, authentication);
    }

    @PutMapping("/skillprofile/{skillProfileId}")
    public SkillProfileDTO putSkillProfile(@PathVariable UUID skillProfileId, @RequestBody SkillProfileDTO skillProfileDTO) {
        return skillProfileRestService.putSkillProfileById(skillProfileId, skillProfileDTO);
    }
}
