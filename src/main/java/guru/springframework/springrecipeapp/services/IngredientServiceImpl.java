package guru.springframework.springrecipeapp.services;

import guru.springframework.springrecipeapp.commands.IngredientCommand;
import guru.springframework.springrecipeapp.converters.IngredientCommandToIngredient;
import guru.springframework.springrecipeapp.converters.IngredientToIngredientCommand;
import guru.springframework.springrecipeapp.domain.Ingredient;
import guru.springframework.springrecipeapp.domain.Recipe;
import guru.springframework.springrecipeapp.repositories.IngredientRepository;
import guru.springframework.springrecipeapp.repositories.RecipeRepository;
import guru.springframework.springrecipeapp.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService {

    private final RecipeRepository recipeRepository;
    private final IngredientRepository ingredientRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final IngredientToIngredientCommand converter;
    private final IngredientCommandToIngredient ingredientCommandToIngredient;

    public IngredientServiceImpl(RecipeRepository recipeRepository, IngredientRepository ingredientRepository,
                                 UnitOfMeasureRepository unitOfMeasureRepository, IngredientToIngredientCommand converter,
                                 IngredientCommandToIngredient ingredientCommandToIngredient) {
        this.recipeRepository = recipeRepository;
        this.ingredientRepository = ingredientRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.converter = converter;
        this.ingredientCommandToIngredient = ingredientCommandToIngredient;
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

    @Override
    @Transactional
    public IngredientCommand saveIngredientCommand(IngredientCommand command) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(command.getRecipeId());
        Recipe recipe = null;
        IngredientCommand savedIngredientCommand = null;
        if (recipeOptional.isPresent()) {
            recipe =recipeOptional.get();
            Optional<Ingredient> ingOptional = ingredientRepository.findById(command.getId());
            if (ingOptional.isPresent()) {
                Ingredient ing = ingOptional.get();
                ing.setDescription(command.getDescription());
                ing.setAmount(command.getAmount());
                ing.setUom(unitOfMeasureRepository.findById(command.getUom().getId())
                        .orElseThrow(() -> new RuntimeException("UoM not found!"))); //todo address this
                Ingredient savedIngredient = ingredientRepository.save(ing);
                savedIngredientCommand = converter.convert(savedIngredient);
            } else {
                recipe.addIngredient(ingredientCommandToIngredient.convert(command));
            }
        } else {
            //todo toss an error if not found
            log.error("Recipe not found for id: "+command.getRecipeId());
            return new IngredientCommand();
        }
        Recipe savedRecipe = recipeRepository.save(recipe);
        return converter.convert(savedRecipe.getIngredients().stream()
                .filter(ing->ing.getId().equals(command.getId())).findFirst().get());
    }
}
