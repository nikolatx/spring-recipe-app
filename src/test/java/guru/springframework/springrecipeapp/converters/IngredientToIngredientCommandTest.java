package guru.springframework.springrecipeapp.converters;

import guru.springframework.springrecipeapp.commands.IngredientCommand;
import guru.springframework.springrecipeapp.domain.Ingredient;
import guru.springframework.springrecipeapp.domain.Recipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class IngredientToIngredientCommandTest {

    public static final Recipe RECIPE = new Recipe();
    public static final BigDecimal AMOUNT = new BigDecimal("1");
    public static final String DESCRIPTION = "Cheeseburger";
    public static final Long ID_VALUE = Long.valueOf(1l);
    public static final Long UOM_ID = Long.valueOf(2L);

    IngredientToIngredientCommand converter;


    @BeforeEach
    void setUp() {
        converter = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
    }

    @Test
    void convert() {
        Ingredient source = new Ingredient();
        source.setRecipe(RECIPE);
        source.setDescription(DESCRIPTION);
        source.setId(ID_VALUE);
        source.setAmount(AMOUNT);

        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand=converter.convert(source);

        assertNotNull(ingredientCommand);
        assertEquals(source.getId(), ingredientCommand.getId());
        assertEquals(source.getDescription(), ingredientCommand.getDescription());
        assertEquals(source.getUom(), ingredientCommand.getUom());

    }
}