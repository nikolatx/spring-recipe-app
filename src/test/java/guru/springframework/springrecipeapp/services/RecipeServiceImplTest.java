package guru.springframework.springrecipeapp.services;

import guru.springframework.springrecipeapp.converters.RecipeCommandToRecipe;
import guru.springframework.springrecipeapp.converters.RecipeToRecipeCommand;
import guru.springframework.springrecipeapp.domain.Recipe;
import guru.springframework.springrecipeapp.exceptions.NotFoundException;
import guru.springframework.springrecipeapp.repositories.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RecipeServiceImplTest {

    RecipeServiceImpl recipeService;

    @Mock
    RecipeRepository recipeRepository;

    @Mock
    RecipeCommandToRecipe recipeCommandToRecipe;

    @Mock
    RecipeToRecipeCommand recipeToRecipeCommand;

    @BeforeEach
    void setUp() {
        recipeService = new RecipeServiceImpl(recipeRepository, recipeCommandToRecipe, recipeToRecipeCommand);
    }

    @Test
    void getRecipes() {
        Recipe recipe = new Recipe();
        HashSet recipeData = new HashSet();
        recipeData.add(recipe);

        when(recipeRepository.findAll()).thenReturn(recipeData);
        Set<Recipe> recipes = recipeService.getRecipes();
        assertEquals(recipes.size(), 1);
        verify(recipeRepository, times(1)).findAll();
    }

    @Test
    void findById() {
        Recipe returnRecipe = Recipe.builder().id(1L).build();
        when(recipeRepository.findById(anyLong())).thenReturn(Optional.of(returnRecipe));

        assertEquals(1L, recipeService.findById(1L).getId());

        verify(recipeRepository, times(1)).findById(anyLong());
        verify(recipeRepository, never()).findAll();
    }

    @Test
    public void testDelete() throws Exception {
        Long recipeIdToDelete=2L;
        recipeService.deleteById(recipeIdToDelete);
        verify(recipeRepository).deleteById(anyLong());
    }

    @Test
    public void getRecipeByIdTestNotFound() throws Exception {
        Exception exception = assertThrows(NotFoundException.class, ()->{
            Optional<Recipe> recipeOptional = Optional.empty();
            when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);
            Recipe recipeReturned = recipeService.findById(1L);

            //exception

        });
    }



}