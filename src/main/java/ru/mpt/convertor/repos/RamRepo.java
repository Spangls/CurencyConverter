package ru.mpt.convertor.repos;

import org.springframework.data.repository.CrudRepository;
import ru.mpt.convertor.model.Ram;
import ru.mpt.convertor.model.RamTech;

import java.util.List;

public interface RamRepo extends CrudRepository<Ram, Integer> {
    List<Ram> findAll();
    List<Ram> findAllByRamTech(RamTech ramTech);
}
