package guru.springframework.springrecipeapp.converters;

import guru.springframework.springrecipeapp.commands.NotesCommand;
import guru.springframework.springrecipeapp.domain.Notes;
import guru.springframework.springrecipeapp.domain.Recipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NotesToNotesCommandTest {

    NotesToNotesCommand converter;

    public static final Long ID=Long.valueOf(1L);
    public static final Recipe RECIPE = new Recipe();
    public static final String NOTES = "notes";

    @BeforeEach
    void setUp() {
        converter = new NotesToNotesCommand();
    }

    @Test
    void testEmpty() {
        assertNotNull(converter.convert(new Notes()));
    }

    @Test
    void testNull() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    void convert() {
        Notes source = new Notes();
        source.setId(ID);
        source.setRecipe(RECIPE);
        source.setNotes(NOTES);
        NotesCommand notesCommand = new NotesCommand();
        notesCommand = converter.convert(source);
        assertNotNull(notesCommand);
        assertEquals(source.getId(), notesCommand.getId());
        assertEquals(source.getNotes(), notesCommand.getNotes());
    }
}