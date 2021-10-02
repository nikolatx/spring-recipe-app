package guru.springframework.springrecipeapp.converters;

import guru.springframework.springrecipeapp.commands.CategoryCommand;
import guru.springframework.springrecipeapp.domain.Category;
import guru.springframework.springrecipeapp.domain.Recipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class CategoryToCategoryCommandTest {

    private static final Long ID = Long.valueOf(1L);
    public static final String DESCRIPTION = "description";

    CategoryToCategoryCommand converter;

    @BeforeEach
    void setUp() {
        converter=new CategoryToCategoryCommand();
    }

    @Test
    void testEmpty() {
        assertNotNull(converter.convert(new Category()));
    }

    @Test
    void testNull() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    void convert() {
        CategoryCommand categoryCommand = new CategoryCommand();
        Category source = new Category();

        source.setId(ID);
        source.setDescription(DESCRIPTION);

        categoryCommand = converter.convert(source);

        assertNotNull(categoryCommand);
        assertEquals(source.getId(), categoryCommand.getId());
        assertEquals(source.getDescription(), categoryCommand.getDescription());
    }
}