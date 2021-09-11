package guru.springframework.springrecipeapp.controllers;

import guru.springframework.springrecipeapp.domain.Recipe;
import guru.springframework.springrecipeapp.repositories.CategoryRepository;
import guru.springframework.springrecipeapp.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Controller
public class IndexController {

    private final RecipeService recipeService;

    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }


    @RequestMapping({"", "/", "/index"})
    public String getIndexPage() {
        /*Optional<Category> categoryOptional = categoryService.findByDescription("American");
        Optional<UnitOfMeasure> unitOfMeasureOptional = .findByDescription("Teaspoon");
        System.out.println("cat id is: " + categoryOptional.get().getId());
        System.out.println("uom id is:" + unitOfMeasureOptional.get().getId());
        */
        log.debug("Getting index page");
        return "index";
    }

    @RequestMapping({"/recipes"})
    public String getRecipes(Model model) {
        model.addAttribute("recipes", recipeService.getRecipes());
        return "recipes/recipes";
    }


}
