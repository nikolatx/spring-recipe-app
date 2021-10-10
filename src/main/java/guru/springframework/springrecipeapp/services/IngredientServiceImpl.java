package guru.springframework.springrecipeapp.services;

import guru.springframework.springrecipeapp.commands.IngredientCommand;
import guru.springframework.springrecipeapp.converters.IngredientToIngredientCommand;
import guru.springframework.springrecipeapp.domain.Recipe;
import guru.springframework.springrecipeapp.repositories.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class IngredientServiceImpl implements IngredientService {

    private final RecipeRepository recipeRepository;
    private final IngredientToIngredientCommand converter;

    public IngredientServiceImpl(RecipeRepository recipeRepository, IngredientToIngredientCommand converter) {
        this.recipeRepository = recipeRepository;
        this.converter = converter;
    }

    @Override
    public IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long id) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);
        IngredientCommand ingredientCommand = null;

        if (recipeOptional.isPresent()) {
            Recipe recipe = recipeOptional.get();
            Optional<IngredientCommand> ingredientCommandOptional = recipe.getIngredients().stream().filter(ing -> ing.getId().equals(id))
                    .map(ing->converter.convert(ing)).findFirst();
            if (ingredientCommandOptional.isPresent())
                ingredientCommand=ingredientCommandOptional.get();
            else
                throw new NullPointerException();
        }
        else
            throw new NullPointerException();

        return ingredientCommand;
    }
}
