package guru.springframework.springrecipeapp.controllers;

import guru.springframework.springrecipeapp.commands.IngredientCommand;
import guru.springframework.springrecipeapp.commands.RecipeCommand;
import guru.springframework.springrecipeapp.commands.UnitOfMeasureCommand;
import guru.springframework.springrecipeapp.services.IngredientService;
import guru.springframework.springrecipeapp.services.RecipeService;
import guru.springframework.springrecipeapp.services.UomService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class IngredientController {

    private final RecipeService recipeService;
    private final IngredientService ingredientService;
    private final UomService uomService;

    public IngredientController(RecipeService recipeService, IngredientService ingredientService, UomService uomService) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
        this.uomService = uomService;
    }

    @RequestMapping("/recipe/{id}/ingredients")
    public String listIngredients(@PathVariable String id, Model model) {
        log.debug("Getting ingredient list for recipe " + id);
        model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(id)));
        return "recipe/ingredient/list";
    }

    @RequestMapping("/recipe/{recipeId}/ingredient/{id}/show")
    public String showRecipeIngredient(@PathVariable String recipeId, @PathVariable String id, Model model) {
        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand = ingredientService.findByRecipeIdAndIngredientId(Long.valueOf(recipeId), Long.valueOf(id));
        model.addAttribute("ingredient", ingredientCommand);
        return "recipe/ingredient/show";
    }

    @RequestMapping("/recipe/{recipeId}/ingredient/{id}/update")
    public String updateIngredient(@PathVariable String recipeId, @PathVariable String id, Model model) {
        model.addAttribute("ingredient", ingredientService.findByRecipeIdAndIngredientId(Long.valueOf(recipeId), Long.valueOf(id)));
        model.addAttribute("uomList", uomService.listAllUoms());
        return "recipe/ingredient/update";
    }

    @PostMapping()
    @RequestMapping("/recipe/{recipeId}/ingredient")
    public String saveOrUpdate(@ModelAttribute IngredientCommand command) {
        IngredientCommand savedCommand = ingredientService.saveIngredientCommand(command);
        return "redirect:/recipe/"+savedCommand.getRecipeId()+"/ingredient/"+savedCommand.getId()+"/show";
    }

    @RequestMapping("/recipe/{recipeId}/ingredient/new")
    public String addIngredient(@PathVariable String recipeId, Model model) {
        IngredientCommand command = new IngredientCommand();
        command.setRecipeId(recipeService.findCommandById(Long.valueOf(recipeId)).getId());
        //todo raise exception if null
        command.setUom(new UnitOfMeasureCommand());
        model.addAttribute("ingredient", command);
        model.addAttribute("uomList", uomService.listAllUoms());
        return "recipe/ingredient/new";
    }

    @RequestMapping("/recipe/{recipeId}/ingredient/{id}/delete")
    public String deleteIngredient(@PathVariable String recipeId, @PathVariable String id, Model model) {
        log.debug("Deleting ingredient id:"+id);
        ingredientService.deleteById(Long.valueOf(recipeId), Long.valueOf(id));
        return "redirect:/recipe/" + recipeId + "/ingredients";
    }

}
