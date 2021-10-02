package guru.springframework.springrecipeapp.converters;

import guru.springframework.springrecipeapp.commands.UnitOfMeasureCommand;
import guru.springframework.springrecipeapp.domain.UnitOfMeasure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UnitOfMeasureToUnitOfMeasureCommandConverterTest {

    UnitOfMeasureToUnitOfMeasureCommand converter;

    @BeforeEach
    void setUp() {
        converter=new UnitOfMeasureToUnitOfMeasureCommand();
    }

    @Test
    void testNull() {
        assertNull(converter.convert(null));
    }

    @Test
    void testEmpty() {
        assertNotNull(converter.convert(new UnitOfMeasure()));
    }

    @Test
    void convert() {
        UnitOfMeasure uom=new UnitOfMeasure();
        uom.setDescription("test");
        uom.setId(1L);

        UnitOfMeasureCommand uomCommand = converter.convert(uom);
        assertNotNull(uomCommand);
        assertEquals(uom.getId(), uomCommand.getId());
        assertEquals(uom.getDescription(), uomCommand.getDescription());
    }
}