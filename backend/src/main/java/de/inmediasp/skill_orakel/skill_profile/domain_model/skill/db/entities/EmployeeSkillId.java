package de.inmediasp.skill_orakel.skill_profile.domain_model.skill.db.entities;



import java.io.Serializable;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;


@Data
@Embeddable
public class EmployeeSkillId implements Serializable {
    
    @Column(name = "skill_profile_id")
    private UUID skillProfileId; 

    @Column(name = "skill_id")
    private UUID skillId; 
}
