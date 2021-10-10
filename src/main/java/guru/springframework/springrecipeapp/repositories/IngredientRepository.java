package guru.springframework.springrecipeapp.repositories;

import guru.springframework.springrecipeapp.commands.IngredientCommand;
import guru.springframework.springrecipeapp.domain.Ingredient;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface IngredientRepository extends CrudRepository<Ingredient, Long> {

    Optional<IngredientCommand> findByRecipeIdAndId(Long recipeId, Long id);

}
