package guru.springframework.springrecipeapp.commands;

import guru.springframework.springrecipeapp.domain.Recipe;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Lob;
import javax.persistence.OneToOne;

@Setter
@Getter
@NoArgsConstructor
public class NotesCommand {
    private Long id;
    private String notes;
}
