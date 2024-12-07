package de.inmediasp.skill_orakel.skill_profile.domain_model.project_assignment.model;

import java.util.UUID;

import de.inmediasp.skill_orakel.skill_profile.domain_model.skill.model.SkillBusinessObject;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectAssignmentSkillBusinessObject {
    @NotNull
    private UUID projektId; 
    @NotNull
    private SkillBusinessObject skill;
}
