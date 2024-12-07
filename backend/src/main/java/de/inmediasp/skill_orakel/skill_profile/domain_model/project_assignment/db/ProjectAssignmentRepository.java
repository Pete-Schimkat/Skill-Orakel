package de.inmediasp.skill_orakel.skill_profile.domain_model.project_assignment.db;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import de.inmediasp.skill_orakel.skill_profile.domain_model.project_assignment.db.entity.project_assignment.ProjectAssignment;
import de.inmediasp.skill_orakel.skill_profile.domain_model.project_assignment.db.entity.project_assignment.ProjectAssignmentId;

public interface ProjectAssignmentRepository extends JpaRepository<ProjectAssignment, ProjectAssignmentId> {

    List<ProjectAssignment> findAllByProjectAssignmentId_SkillProfileId(UUID skillProfileId);
    void deleteAllByProjectAssignmentId_SkillProfileId(UUID skillProfileId); 
}
