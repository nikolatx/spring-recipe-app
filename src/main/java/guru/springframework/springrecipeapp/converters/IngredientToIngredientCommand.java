package guru.springframework.springrecipeapp.converters;

import guru.springframework.springrecipeapp.commands.IngredientCommand;
import guru.springframework.springrecipeapp.commands.UnitOfMeasureCommand;
import guru.springframework.springrecipeapp.domain.Ingredient;
import lombok.Synchronized;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class IngredientToIngredientCommand implements Converter<Ingredient, IngredientCommand> {

    private final UnitOfMeasureToUnitOfMeasureCommand uomConverter;

    public IngredientToIngredientCommand(UnitOfMeasureToUnitOfMeasureCommand uomConverter) {
        this.uomConverter = uomConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public IngredientCommand convert(Ingredient source) {

        if (source==null)
            return null;

        final IngredientCommand ingredientCommand=new IngredientCommand();
        ingredientCommand.setId(source.getId());
        if (source.getRecipe() != null)
            ingredientCommand.setRecipeId(source.getRecipe().getId());
        if (source.getDescription()!=null)
            ingredientCommand.setDescription(source.getDescription());
        if (source.getAmount()!=null)
            ingredientCommand.setAmount(source.getAmount());
        UnitOfMeasureCommand uomCommand = uomConverter.convert(source.getUom());
        if (source.getUom()!=null)
            ingredientCommand.setUom(uomCommand);

        return ingredientCommand;
    }
}
