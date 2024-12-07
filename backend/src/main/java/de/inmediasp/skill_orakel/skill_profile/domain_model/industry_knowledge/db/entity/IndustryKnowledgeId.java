package de.inmediasp.skill_orakel.skill_profile.domain_model.industry_knowledge.db.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class IndustryKnowledgeId implements Serializable {
    
    @Column(name = "skill_profile_id")
    private UUID skillProfileId;

    @Column(name = "industry_id")
    private UUID industryId;
}
