package de.inmediasp.skill_orakel.skill_profile.domain_model.qualification.db.entity;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "qualification")
public class Qualification {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "qualification_id")
    private UUID id;

    @Column(name = "skill_profile_id")
    private UUID skillProfileId;
    
    @Column(name = "title")
    private String title;
    
    @Column(name = "issuer")
    private String issuer;

    @Column(name = "description")
    private String description;

    @Column(name = "date")
    private LocalDate date;
}
