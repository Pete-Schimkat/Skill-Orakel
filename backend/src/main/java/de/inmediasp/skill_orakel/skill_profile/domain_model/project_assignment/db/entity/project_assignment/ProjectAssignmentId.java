package de.inmediasp.skill_orakel.skill_profile.domain_model.project_assignment.db.entity.project_assignment;

import java.io.Serializable;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class ProjectAssignmentId implements Serializable {
    @Column(name = "project_id")
    private UUID projectId;

    @Column(name = "skill_profile_id")
    private UUID skillProfileId;

}