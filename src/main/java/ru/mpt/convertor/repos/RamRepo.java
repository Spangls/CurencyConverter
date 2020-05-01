package ru.mpt.convertor.repos;

import org.springframework.data.repository.CrudRepository;
import ru.mpt.convertor.model.Ram;
import ru.mpt.convertor.model.RamTech;

import java.util.List;
import java.util.Set;

public interface RamRepo extends CrudRepository<Ram, Integer> {
    List<Ram> findAll();

    Set<Ram> findAllByRamTech(RamTech ramTech);

    Ram findFirstById(Integer id);
}
