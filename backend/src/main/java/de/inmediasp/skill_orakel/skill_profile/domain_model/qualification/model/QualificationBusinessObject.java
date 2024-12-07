package de.inmediasp.skill_orakel.skill_profile.domain_model.qualification.model;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QualificationBusinessObject {

    @NotNull
    private UUID id;
    @NotNull
    private String title;
    @NotNull
    private String issuer;
    @NotNull
    private String description;
    @NotNull
    private LocalDate date;
}
