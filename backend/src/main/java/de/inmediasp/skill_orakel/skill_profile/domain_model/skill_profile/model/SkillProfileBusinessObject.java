package de.inmediasp.skill_orakel.skill_profile.domain_model.skill_profile.model;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

import de.inmediasp.skill_orakel.skill_profile.domain_model.basic_info.model.BasicInfoBusinessObject;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SkillProfileBusinessObject {
    @NotNull
    private UUID id;
    @NotNull
    private BasicInfoBusinessObject basicInfo;
    @NotNull
    private String employeeIdentifier;
}
