package guru.springframework.springrecipeapp.services;

import guru.springframework.springrecipeapp.commands.IngredientCommand;
import guru.springframework.springrecipeapp.converters.IngredientToIngredientCommand;
import guru.springframework.springrecipeapp.converters.UnitOfMeasureToUnitOfMeasureCommand;
import guru.springframework.springrecipeapp.domain.Ingredient;
import guru.springframework.springrecipeapp.domain.Recipe;
import guru.springframework.springrecipeapp.repositories.IngredientRepository;
import guru.springframework.springrecipeapp.repositories.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class IngredientServiceImplTest {

    @Mock
    IngredientRepository ingredientRepository;

    @Mock
    RecipeRepository recipeRepository;

    private IngredientToIngredientCommand converter;

    IngredientServiceImpl service;

    @BeforeEach
    void setUp() {
        converter=new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
        service=new IngredientServiceImpl(recipeRepository,converter);
    }

    @Test
    void findByRecipeIdAndIdHappy() {
        Recipe recipe = Recipe.builder().id(3L).build();
        recipe.setIngredients(new HashSet<>());
        Ingredient ing1 = new Ingredient();
        ing1.setId(3L);

        Ingredient ing2 = new Ingredient();
        ing2.setId(2L);

        Ingredient ing3 = new Ingredient();
        ing3.setId(3L);

        recipe.addIngredient(ing1);
        recipe.addIngredient(ing2);
        recipe.addIngredient(ing3);

        Optional<Recipe> recipeOptional = Optional.of(recipe);

        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

        IngredientCommand ingComm = service.findByRecipeIdAndIngredientId(3L, 2L);

        assertEquals(ingComm.getId(), 2L);
        assertEquals(ingComm.getRecipeId(), 3L);
        verify(recipeRepository, times(1)).findById(anyLong());
    }
}