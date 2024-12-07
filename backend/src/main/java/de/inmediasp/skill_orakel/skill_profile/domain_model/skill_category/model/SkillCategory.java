package de.inmediasp.skill_orakel.skill_profile.domain_model.skill_category.model;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "skill_category")
@IdClass(SkillCategory.class)
@NoArgsConstructor
public class SkillCategory {

    @Id
    private UUID categoryId; 

    @Id
    private UUID skillId;
}
