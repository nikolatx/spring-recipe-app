package guru.springframework.springrecipeapp.controllers;

import guru.springframework.springrecipeapp.commands.RecipeCommand;
import guru.springframework.springrecipeapp.domain.Recipe;
import guru.springframework.springrecipeapp.exceptions.NotFoundException;
import guru.springframework.springrecipeapp.services.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(MockitoExtension.class)
@WebAppConfiguration()
@AutoConfigureMockMvc
class RecipeControllerTest {

    public static final String RECIPE_RECIPEFORM_URL = "recipe/recipeform";
    @Mock
    RecipeService recipeService;

    @InjectMocks
    RecipeController recipeController;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        recipeController = new RecipeController(recipeService);
        mockMvc = MockMvcBuilders.standaloneSetup(recipeController)
                .setControllerAdvice(new ControllerExceptionHandler())
                .build();
    }

    @Test
    void getRecipe() throws Exception {

        Recipe recipe = Recipe.builder().id(1L).build();
        when(recipeService.findById(anyLong())).thenReturn(recipe);

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(recipeController).build();

        mockMvc.perform(MockMvcRequestBuilders.get("/recipe/1/show"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/show"))
                .andExpect(model().attributeExists("recipe"));
    }

    @Test
    void testGetRecipeNotFound() throws Exception {
        when(recipeService.findById(anyLong())).thenThrow(NotFoundException.class);
        mockMvc.perform(MockMvcRequestBuilders.get("/recipe/1/show"))
                .andExpect(status().isNotFound())
                .andExpect(view().name("404error"));
    }

    @Test
    void testNumberFormatException() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/recipe/d/show"))
                .andExpect(status().isBadRequest())
                .andExpect(view().name("400error"));
    }

    @Test
    void testGetNewRecipeForm() throws Exception {
        RecipeCommand command = new RecipeCommand();
        mockMvc.perform(MockMvcRequestBuilders.get("/recipe/new"))
                .andExpect(status().isOk())
                .andExpect(view().name(RECIPE_RECIPEFORM_URL))
                .andExpect(model().attributeExists("recipe"));
    }

    @Test
    void postNewRecipeForm() throws Exception {
        RecipeCommand command = new RecipeCommand();
        command.setId(2l);
        when(recipeService.saveRecipeCommand(any())).thenReturn(command);
        mockMvc.perform(MockMvcRequestBuilders.post("/recipe")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("id", "")
                        .param("description", "some string")
                        .param("directions", "some directions"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/recipe/2/show"));
    }

    @Test
    void postNewRecipeFormValidationFailed() throws Exception {
        RecipeCommand command = new RecipeCommand();
        command.setId(2L);
        lenient().when(recipeService.saveRecipeCommand(any())).thenReturn(command);
        mockMvc.perform(MockMvcRequestBuilders.post("/recipe")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("id", "")
                        )
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("recipe"))
                .andExpect(view().name(RECIPE_RECIPEFORM_URL));
    }

    @Test
    public void testGetUpdateView() throws Exception {
        RecipeCommand command = new RecipeCommand();
        command.setId(2L);
        when(recipeService.findCommandById(anyLong())).thenReturn(command);
        mockMvc.perform(MockMvcRequestBuilders.get("/recipe/1/update"))
                .andExpect(status().isOk())
                .andExpect(view().name(RECIPE_RECIPEFORM_URL))
                .andExpect(model().attributeExists("recipe"));
    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/recipe/1/delete"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"));
        verify(recipeService, times(1)).deleteById(anyLong());
    }


}