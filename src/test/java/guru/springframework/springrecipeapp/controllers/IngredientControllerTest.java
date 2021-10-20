package guru.springframework.springrecipeapp.controllers;

import guru.springframework.springrecipeapp.commands.IngredientCommand;
import guru.springframework.springrecipeapp.commands.RecipeCommand;
import guru.springframework.springrecipeapp.domain.Ingredient;
import guru.springframework.springrecipeapp.domain.Recipe;
import guru.springframework.springrecipeapp.domain.UnitOfMeasure;
import guru.springframework.springrecipeapp.repositories.UnitOfMeasureRepository;
import guru.springframework.springrecipeapp.services.IngredientService;
import guru.springframework.springrecipeapp.services.RecipeService;
import guru.springframework.springrecipeapp.services.UomService;
import guru.springframework.springrecipeapp.services.UomServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class IngredientControllerTest {

    @Mock
    RecipeService recipeService;

    @Mock
    IngredientService ingredientService;

    @Mock
    UomService uomService;

    @Mock
    UnitOfMeasureRepository uomRepository;

    IngredientController controller;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        controller = new IngredientController(recipeService, ingredientService, uomService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }


    @Test
    public void testListIngredients() throws Exception {
        //given
        RecipeCommand recipeCommand = new RecipeCommand();
        when(recipeService.findCommandById(anyLong())).thenReturn(recipeCommand);
        //when
        mockMvc.perform(MockMvcRequestBuilders.get("/recipe/1/ingredients"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/ingredient/list"))
                .andExpect(model().attributeExists("recipe"));
        //then
        verify(recipeService).findCommandById(anyLong());
    }

    @Test
    public void testShowIngredient() throws Exception {
        //given
        IngredientCommand command = new IngredientCommand();
        //when
        when(ingredientService.findByRecipeIdAndIngredientId(anyLong(), anyLong())).thenReturn(command);
        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/recipe/1/ingredient/1/show"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/ingredient/show"))
                .andExpect(model().attributeExists("ingredient"));
    }

    @Test
    public void testUpdateIngredientForm() throws Exception {
        IngredientCommand ingredientCommand = new IngredientCommand();

        when(ingredientService.findByRecipeIdAndIngredientId(anyLong(), anyLong())).thenReturn(ingredientCommand);
        when(uomService.listAllUoms()).thenReturn(new HashSet<>());

        mockMvc.perform(MockMvcRequestBuilders.get("/recipe/1/ingredient/2/update"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/ingredient/update"))
                .andExpect(model().attributeExists("ingredient"))
                .andExpect(model().attributeExists("uomList"));
    }

    @Test
    public void testSaveOrUpdate() throws Exception {
        IngredientCommand command = new IngredientCommand();
        command.setId(3L);
        command.setRecipeId(2L);

        when(ingredientService.saveIngredientCommand(any())).thenReturn(command);

        mockMvc.perform(MockMvcRequestBuilders.post("/recipe/2/ingredient")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "")
                .param("description", "some string"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/recipe/2/ingredient/3/show"));

    }

    @Test
    public void testAddNewIngredient() throws Exception {
        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(2L);
        IngredientCommand command = new IngredientCommand();
        command.setRecipeId(2L);

        when(recipeService.findCommandById(anyLong())).thenReturn(recipeCommand);
        when(uomService.listAllUoms()).thenReturn(new HashSet<>());

        mockMvc.perform(MockMvcRequestBuilders.get("/recipe/2/ingredient/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/ingredient/new"))
                .andExpect(model().attributeExists("ingredient"));
        verify(recipeService, times(1)).findCommandById(anyLong());
    }

    @Test
    void testDeleteIngredient() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/recipe/1/ingredient/1/delete"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/recipe/1/ingredients"));
        verify(ingredientService).deleteById(anyLong(), anyLong());
    }
}