package de.inmediasp.skill_orakel.skill_profile.domain_model.industry_knowledge.model;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IndustryBusinessObject {

    @NotNull
    private UUID id;
    @NotNull
    private String name;
}
