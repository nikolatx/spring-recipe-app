package guru.springframework.springrecipeapp.converters;

import guru.springframework.springrecipeapp.commands.NotesCommand;
import guru.springframework.springrecipeapp.commands.RecipeCommand;
import guru.springframework.springrecipeapp.domain.Notes;
import guru.springframework.springrecipeapp.domain.Recipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NotesCommandToNotesTest {

    public static final Long ID=Long.valueOf(1L);
    public static final String NOTES = "notes";

    NotesCommandToNotes converter;

    @BeforeEach
    void setUp() {
        converter = new NotesCommandToNotes();
    }

    @Test
    void testEmpty() {
        assertNotNull(converter.convert(new NotesCommand()));
    }

    @Test
    void testNull() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    void convert() {

        NotesCommand source = new NotesCommand();
        Notes notes = new Notes();
        source.setId(ID);
        source.setNotes(NOTES);

        notes = converter.convert(source);
        assertEquals(source.getId(), notes.getId());
        assertEquals(source.getNotes(), notes.getNotes());
    }
}