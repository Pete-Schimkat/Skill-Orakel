package de.inmediasp.skill_orakel.skill_profile.domain_model.language_knowledge.model;

import java.util.UUID;

import de.inmediasp.skill_orakel.skill_profile.domain_model.language_knowledge.db.entity.LanguageLevel;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LanguageKnowledgeBusinessObject {
    @NotNull
    private LanguageLevel languageLevel;
    
    @NotNull
    private UUID languageId;
    
    @NotNull
    private String languageName;
}
