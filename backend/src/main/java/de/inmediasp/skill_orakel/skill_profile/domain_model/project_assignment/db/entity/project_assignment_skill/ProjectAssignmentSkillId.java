package de.inmediasp.skill_orakel.skill_profile.domain_model.project_assignment.db.entity.project_assignment_skill;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Data
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class ProjectAssignmentSkillId implements Serializable {
    @Column(name = "project_id")
    private UUID projectId;

    @Column(name = "skill_profile_id")
    private UUID skillProfileId;

    @Column(name = "skill_id")
    private UUID skillId;

}
