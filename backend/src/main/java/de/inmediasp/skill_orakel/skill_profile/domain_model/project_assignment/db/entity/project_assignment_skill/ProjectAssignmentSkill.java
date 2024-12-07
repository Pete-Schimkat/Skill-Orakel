package de.inmediasp.skill_orakel.skill_profile.domain_model.project_assignment.db.entity.project_assignment_skill;


import de.inmediasp.skill_orakel.skill_profile.domain_model.skill.db.entities.Skill;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "project_assignment_skill")
@NoArgsConstructor
public class ProjectAssignmentSkill {

    @EmbeddedId
    private ProjectAssignmentSkillId projectAssignmentSkillId;

    @MapsId("skillId")
    @OneToOne
    @JoinColumn(name = "skill_id", referencedColumnName = "skill_id")
    private Skill skill;
}
