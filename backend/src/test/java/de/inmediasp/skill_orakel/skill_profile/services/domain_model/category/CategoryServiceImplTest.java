package de.inmediasp.skill_orakel.skill_profile.services.domain_model.category;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import de.inmediasp.skill_orakel.skill_profile.domain_model.skill_category.CategoryService;
import de.inmediasp.skill_orakel.skill_profile.domain_model.skill_category.db.CategoryRepository;
import de.inmediasp.skill_orakel.skill_profile.domain_model.skill_category.model.Category;
import de.inmediasp.skill_orakel.skill_profile.domain_model.skill_category.service.CategoryServiceImpl;

public class CategoryServiceImplTest {
    private final CategoryRepository repository = mock(CategoryRepository.class); 
    private final CategoryService service = new CategoryServiceImpl(repository); 

    @Test
    void GIVEN_has_no_entry_THEN_find_by_id_RETURNS_Null() {
        Optional<Category> optional = Optional.empty();
        var id = UUID.randomUUID(); 
        when(repository.findById(id)).thenReturn(optional); 

        var result = service.findById(id); 
        assertNull(result);
    }

    @Test
    void GIVEN_db_has_no_entry_THEN_find_all_RETURNS_empty_list() {
        List<Category> categories = new ArrayList<>();

        when(repository.findAll()).thenReturn(categories); 
        var result = service.findAll(); 

        assertTrue(result.isEmpty());
    }

    @Test
    void GIVEN_db_has_entry_with_id_THEN_find_by_id_RETURNS_element() {
        Category category = new Category(); 
        Optional<Category> optional = Optional.of(category); 
        UUID id = UUID.randomUUID(); 

        when(repository.findById(id)).thenReturn(optional);
        var result = service.findById(id); 

        assertEquals(category, result);
    }


    @Test
    void GIVEN_db_has_more_than_zero_entries_THEN_find_all_RETURNS_list_of_all_elements() {
        List<Category> categories = List.of(new Category(), new Category(), new Category());

        when(repository.findAll()).thenReturn(categories); 
        var result = service.findAll(); 

        assertEquals(3, result.size()); 
        assertEquals(categories, result);

    }
}
