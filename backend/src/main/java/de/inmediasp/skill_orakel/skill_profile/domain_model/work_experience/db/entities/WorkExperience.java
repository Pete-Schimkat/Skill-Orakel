package de.inmediasp.skill_orakel.skill_profile.domain_model.work_experience.db.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
@Table(name = "work_experience")
public class WorkExperience {

    @Id
    @Column(name = "work_experience_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "skill_profile_id")
    private UUID skillProfileId;
    @Column(name = "employer")
    private String employer;
    @Column(name = "description")
    private String description;
    @Column(name = "start_date")
    private LocalDate startDate;
    @Column(name = "end_date")
    private LocalDate endDate;
}
