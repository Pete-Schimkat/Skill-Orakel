package de.inmediasp.skill_orakel.skill_profile.domain_model.skill.db.entities;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "skill")
public class Skill {
    
    @Id
    @Column(name = "skill_id")
    private UUID skillId;

    @Column(name = "name")
    private String name;
}
