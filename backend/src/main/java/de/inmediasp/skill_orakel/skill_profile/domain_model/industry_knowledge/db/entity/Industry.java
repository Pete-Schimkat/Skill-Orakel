package de.inmediasp.skill_orakel.skill_profile.domain_model.industry_knowledge.db.entity;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "industry")
public class Industry {
    
    @Id
    @Column(name = "industry_id")
    private UUID id;

    @Column(name = "name")
    private String name;
}
