package ru.mpt.convertor.repos;

import org.springframework.data.repository.CrudRepository;
import ru.mpt.convertor.model.PowerSupply;

import java.util.Set;

public interface PowerSupplyRepo extends CrudRepository<PowerSupply, Integer> {
    Set<PowerSupply> findAll();

    PowerSupply findFirstById(Integer id);
}
