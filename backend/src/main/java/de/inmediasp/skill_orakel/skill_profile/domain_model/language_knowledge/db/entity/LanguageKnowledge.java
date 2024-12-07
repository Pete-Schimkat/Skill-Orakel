package de.inmediasp.skill_orakel.skill_profile.domain_model.language_knowledge.db.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "language_knowledge")
public class LanguageKnowledge {

    @EmbeddedId
    private LanguageKnowledgeId languageKnowledgeId;
    
    @MapsId("language_id")
    @OneToOne
    @JoinColumn(name = "language_id", referencedColumnName = "language_id")
    private Language language;

    @Column(name = "language_level")
    private LanguageLevel languageLevel;
}
