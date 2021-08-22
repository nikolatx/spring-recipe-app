package guru.springframework.springrecipeapp.repositories;

import guru.springframework.springrecipeapp.domain.Ingredient;
import org.springframework.data.repository.CrudRepository;

public interface IngredientRepository extends CrudRepository<Ingredient, Long> {
}
