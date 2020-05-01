package ru.mpt.convertor.repos;

import org.springframework.data.repository.CrudRepository;
import ru.mpt.convertor.model.PowerSupply;

import java.util.List;

public interface PowerSupplyRepo extends CrudRepository<PowerSupply, Integer> {
    List<PowerSupply> findAll();

    PowerSupply findFirstById(Integer id);
}
