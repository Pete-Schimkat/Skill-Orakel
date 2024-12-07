package de.inmediasp.skill_orakel.skill_profile.domain_model.skill_profile.db.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

import de.inmediasp.skill_orakel.skill_profile.domain_model.basic_info.db.entity.BasicInfo;

@Data
@NoArgsConstructor
@Entity
@Table(name = "skill_profile")
public class SkillProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "skill_profile_id")
    private UUID id;

    @OneToOne
    @JoinColumn(name = "basic_info_id", referencedColumnName = "basic_info_id")
    private BasicInfo basicInfo;

    @Column(name = "oauth2_subject_identifier")
    private String employeeIdentifier;
}
