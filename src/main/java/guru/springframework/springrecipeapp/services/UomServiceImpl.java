package guru.springframework.springrecipeapp.services;

import guru.springframework.springrecipeapp.commands.UnitOfMeasureCommand;
import guru.springframework.springrecipeapp.converters.UnitOfMeasureToUnitOfMeasureCommand;
import guru.springframework.springrecipeapp.repositories.UnitOfMeasureRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UomServiceImpl implements UomService {

    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final UnitOfMeasureToUnitOfMeasureCommand converter;

    public UomServiceImpl(UnitOfMeasureRepository unitOfMeasureRepository, UnitOfMeasureToUnitOfMeasureCommand converter) {
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.converter = converter;
    }

    @Override
    public Set<UnitOfMeasureCommand> listAllUoms() {
        Set<UnitOfMeasureCommand> uomCommands = new HashSet<>();
        unitOfMeasureRepository.findAll().iterator().forEachRemaining(e->uomCommands.add(converter.convert(e)));
        return uomCommands;
    }

}
