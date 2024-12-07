package de.inmediasp.skill_orakel.skill_profile.domain_model.skill_category.db;

import org.springframework.data.jpa.repository.JpaRepository;

import de.inmediasp.skill_orakel.skill_profile.domain_model.skill_category.model.Category;

import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {
    
}
