package de.inmediasp.skill_orakel.skill_profile.domain_model.project_assignment.db.entity.project_assignment;

import de.inmediasp.skill_orakel.skill_profile.domain_model.project_assignment.db.entity.project.Project;
import de.inmediasp.skill_orakel.skill_profile.domain_model.project_assignment.db.entity.project_assignment_skill.ProjectAssignmentSkill;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "project_assignment")
public class ProjectAssignment {

    @EmbeddedId
    private ProjectAssignmentId projectAssignmentId;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "responsability")
    private String responsibilities;

    @MapsId("projectId")
    @OneToOne
    @JoinColumn(name = "project_id", referencedColumnName = "project_id")
    private Project project;

    @OneToMany
    @JoinColumns({
            @JoinColumn(name = "project_id", referencedColumnName = "project_id"),
            @JoinColumn(name = "skill_profile_id", referencedColumnName = "skill_profile_id")
    })
    private List<ProjectAssignmentSkill> projectAssignmentSkills;
}