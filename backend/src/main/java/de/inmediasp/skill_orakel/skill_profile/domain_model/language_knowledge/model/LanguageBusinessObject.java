package de.inmediasp.skill_orakel.skill_profile.domain_model.language_knowledge.model;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LanguageBusinessObject {

    @NotNull
    private UUID languageId;
    @NotNull
    private String languageName;
}
