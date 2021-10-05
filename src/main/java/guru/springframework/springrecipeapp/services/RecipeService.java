package guru.springframework.springrecipeapp.services;

import guru.springframework.springrecipeapp.commands.RecipeCommand;
import guru.springframework.springrecipeapp.domain.Recipe;

import java.util.Set;

public interface RecipeService {

    public Set<Recipe> getRecipes();

    public Recipe findById(Long id);

    RecipeCommand saveRecipeCommand(RecipeCommand command);

    public RecipeCommand findCommandById(Long id);

    void deleteById(Long id);
}
