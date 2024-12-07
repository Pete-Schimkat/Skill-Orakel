package de.inmediasp.skill_orakel.skill_profile.domain_model.project_assignment.db;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import de.inmediasp.skill_orakel.skill_profile.domain_model.project_assignment.db.entity.project.Project;

public interface ProjectRepository extends JpaRepository<Project, UUID> {
    

    
}
