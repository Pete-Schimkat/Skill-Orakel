package de.inmediasp.skill_orakel.skill_profile.domain_model.language_knowledge.db.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class LanguageKnowledgeId implements Serializable {
    @Column(name = "skill_profile_id")
    private UUID skillProfileId;

    @Column(name = "language_id")
    private UUID languageId;
}

