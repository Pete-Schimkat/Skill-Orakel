package de.inmediasp.skill_orakel.skill_profile.domain_model.skill.model;



import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SkillBusinessObject {
    @NotNull
    private UUID id; 
    @NotNull
    private String name; 
}
