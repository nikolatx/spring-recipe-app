package guru.springframework.springrecipeapp.converters;

import guru.springframework.springrecipeapp.commands.IngredientCommand;
import guru.springframework.springrecipeapp.commands.UnitOfMeasureCommand;
import guru.springframework.springrecipeapp.domain.Ingredient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class IngredientCommandToIngredientTest {

    public static final BigDecimal AMOUNT = new BigDecimal("1");
    public static final String DESCRIPTION = "Cheeseburger";
    public static final Long ID = Long.valueOf(1L);
    public static final UnitOfMeasureCommand UOM = new UnitOfMeasureCommand();

    IngredientCommandToIngredient converter;



    @BeforeEach
    void setUp() {
        converter = new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure());
    }

    @Test
    void testEmpty() {
        assertNotNull(converter.convert(new IngredientCommand()));
    }

    @Test
    void testNull() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    void convert() {
        Ingredient ingredient = new Ingredient();
        IngredientCommand source = new IngredientCommand();
        source.setId(ID);
        source.setDescription(DESCRIPTION);
        source.setUom(UOM);
        source.setAmount(AMOUNT);

        ingredient = converter.convert(source);
        assertNotNull(ingredient);
        assertEquals(ingredient.getId(), source.getId());
        assertEquals(source.getDescription(), ingredient.getDescription());
        assertEquals(source.getAmount(), ingredient.getAmount());
    }
}