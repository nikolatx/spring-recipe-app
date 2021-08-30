package guru.springframework.springrecipeapp.controllers;

import guru.springframework.springrecipeapp.domain.Recipe;
import guru.springframework.springrecipeapp.repositories.CategoryRepository;
import guru.springframework.springrecipeapp.services.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.ui.Model;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class IndexControllerTest {

    IndexController indexController;

    @Mock
    CategoryRepository categoryRepository;

    @Mock
    RecipeService recipeService;

    @Mock
    Model model;

    @BeforeEach
    void setUp() {
        indexController = new IndexController(categoryRepository, recipeService);
    }

    @Test
    void getRecipes() {
        Recipe recipe = new Recipe();
        recipe.setId(3L);
        HashSet<Recipe> recipes = new HashSet();
        recipes.add(recipe);

        when(recipeService.getRecipes()).thenReturn(recipes);
        Set<Recipe> returnedRecipes = recipeService.getRecipes();
        assertEquals(returnedRecipes.size(), 1);
        //verify(recipeService, times(1).
    }
}