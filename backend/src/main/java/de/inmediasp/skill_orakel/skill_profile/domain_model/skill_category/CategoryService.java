package de.inmediasp.skill_orakel.skill_profile.domain_model.skill_category;

import java.util.List;
import java.util.UUID;

import de.inmediasp.skill_orakel.skill_profile.domain_model.skill_category.model.Category;

public interface CategoryService {
    Category findById(UUID id); 
    List<Category> findAll(); 
}
