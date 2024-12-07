package de.inmediasp.skill_orakel.skill_profile.domain_model.project_assignment.model;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import de.inmediasp.skill_orakel.skill_profile.domain_model.skill.model.SkillBusinessObject;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectAssignmentBusinessObject {
    @NotNull
    private UUID projectId;
    @NotNull
    private String name;
    @NotNull
    private String description;
    @NotNull
    private String responsibilities;
    @NotNull
    private String customer;
    @NotNull
    private LocalDate startDate;
    
    private LocalDate endDate;
    @NotNull
    private List<SkillBusinessObject> skills;
}