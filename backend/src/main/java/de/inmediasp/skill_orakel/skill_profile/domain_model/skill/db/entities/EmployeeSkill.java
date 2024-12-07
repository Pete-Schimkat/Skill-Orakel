package de.inmediasp.skill_orakel.skill_profile.domain_model.skill.db.entities;



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
@NoArgsConstructor
@Table(name = "employee_skill")
public class EmployeeSkill {

    @EmbeddedId
    private EmployeeSkillId employeeSkillId;

    @MapsId("skillId")
    @OneToOne
    @JoinColumn(name = "skill_id", referencedColumnName = "skill_id")
    private Skill skill;
}
