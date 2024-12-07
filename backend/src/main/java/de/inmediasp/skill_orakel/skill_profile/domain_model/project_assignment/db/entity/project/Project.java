package de.inmediasp.skill_orakel.skill_profile.domain_model.project_assignment.db.entity.project;

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
@Table(name = "project")
public class Project {

    @Id
    @Column(name = "project_id")
    private UUID projectId;

    @Column(name = "title")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "customer")
    private String customer;
}
