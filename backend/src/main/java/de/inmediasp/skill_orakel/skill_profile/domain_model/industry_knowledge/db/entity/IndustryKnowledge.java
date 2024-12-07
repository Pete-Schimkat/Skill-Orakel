package de.inmediasp.skill_orakel.skill_profile.domain_model.industry_knowledge.db.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "industry_knowledge")
public class IndustryKnowledge {

    @EmbeddedId
    private IndustryKnowledgeId industryKnowledgeId;

    @OneToOne
    @MapsId("industryId")
    @JoinColumn(name = "industry_id", referencedColumnName = "industry_id")
    private Industry industry;
}
