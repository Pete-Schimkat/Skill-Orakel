package de.inmediasp.skill_orakel.skill_profile.domain_model.skill_category.model;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "category")
public class Category {
    
    @Id
    private UUID id; 

    @Column(name = "name")
    private String name; 
}
