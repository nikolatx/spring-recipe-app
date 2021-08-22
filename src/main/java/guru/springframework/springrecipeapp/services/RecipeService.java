package guru.springframework.springrecipeapp.services;

import guru.springframework.springrecipeapp.domain.Recipe;

import java.util.Set;

public interface RecipeService {

    public Set<Recipe> getRecipes();

}
