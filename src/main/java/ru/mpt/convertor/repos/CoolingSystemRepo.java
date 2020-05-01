package ru.mpt.convertor.repos;

import org.springframework.data.repository.CrudRepository;
import ru.mpt.convertor.model.CoolingSystem;

import java.util.List;

public interface CoolingSystemRepo extends CrudRepository<CoolingSystem, Integer> {
    List<CoolingSystem> findAll();
}
