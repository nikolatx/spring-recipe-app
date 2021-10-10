package guru.springframework.springrecipeapp.services;


import guru.springframework.springrecipeapp.commands.IngredientCommand;
import guru.springframework.springrecipeapp.domain.Ingredient;

import java.util.Optional;

public interface IngredientService {

    IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long id);

}
