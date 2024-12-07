package de.inmediasp.skill_orakel.skill_profile.domain_model.skill_category.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.inmediasp.skill_orakel.skill_profile.domain_model.skill_category.CategoryService;
import de.inmediasp.skill_orakel.skill_profile.domain_model.skill_category.db.CategoryRepository;
import de.inmediasp.skill_orakel.skill_profile.domain_model.skill_category.model.Category;

import java.util.List;
import java.util.UUID;


@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository; 
    }

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Category findById(UUID id) {
        return categoryRepository.findById(id).orElse(null);
    }


}
