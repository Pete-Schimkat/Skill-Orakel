package de.inmediasp.skill_orakel.skill_profile.domain_model.project_assignment.model;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectBusinessObject {
    
    @NotNull
    private UUID id;

    @NotNull
    private String name; 

    @NotNull
    private String description; 

    @NotNull
    private String customer; 
}
