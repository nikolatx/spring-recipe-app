package guru.springframework.springrecipeapp.services;

import guru.springframework.springrecipeapp.commands.UnitOfMeasureCommand;

import java.util.Set;

public interface UomService {

    public Set<UnitOfMeasureCommand> listAllUoms();

}
