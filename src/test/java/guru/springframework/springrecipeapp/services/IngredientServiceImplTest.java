package guru.springframework.springrecipeapp.services;

import guru.springframework.springrecipeapp.commands.IngredientCommand;
import guru.springframework.springrecipeapp.converters.IngredientCommandToIngredient;
import guru.springframework.springrecipeapp.converters.IngredientToIngredientCommand;
import guru.springframework.springrecipeapp.converters.UnitOfMeasureCommandToUnitOfMeasure;
import guru.springframework.springrecipeapp.converters.UnitOfMeasureToUnitOfMeasureCommand;
import guru.springframework.springrecipeapp.domain.Ingredient;
import guru.springframework.springrecipeapp.domain.Recipe;
import guru.springframework.springrecipeapp.repositories.IngredientRepository;
import guru.springframework.springrecipeapp.repositories.RecipeRepository;
import guru.springframework.springrecipeapp.repositories.UnitOfMeasureRepository;
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

    @Mock
    UnitOfMeasureRepository unitOfMeasureRepository;

    private IngredientToIngredientCommand converter;
    private IngredientCommandToIngredient converter2;

    IngredientServiceImpl service;

    @BeforeEach
    void setUp() {
        converter=new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
        converter2 = new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure());
        service=new IngredientServiceImpl(recipeRepository, ingredientRepository, unitOfMeasureRepository, converter, converter2);
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

    @Test
    void saveIngredientCommand() throws Exception {
        IngredientCommand command = new IngredientCommand();
        command.setId(3L);
        command.setRecipeId(2L);

        Optional<Recipe> recipeOptional = Optional.of(new Recipe());

        Recipe savedRecipe = new Recipe();
        savedRecipe.addIngredient(new Ingredient());
        savedRecipe.getIngredients().iterator().next().setId(3L);

        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);
        when(recipeRepository.save(any())).thenReturn(savedRecipe);

        IngredientCommand savedCommand = service.saveIngredientCommand(command);

        assertEquals(Long.valueOf(3L), savedCommand.getId());
        verify(recipeRepository, times(1)).findById(anyLong());
        verify(recipeRepository, times(1)).save(any(Recipe.class));
    }
}