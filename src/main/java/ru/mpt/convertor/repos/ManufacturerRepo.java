package ru.mpt.convertor.repos;

import org.springframework.data.repository.CrudRepository;
import ru.mpt.convertor.model.Manufacturer;

import java.util.Set;

public interface ManufacturerRepo extends CrudRepository<Manufacturer, Integer> {
    Manufacturer findFirstByTitle(String title);
    Set<Manufacturer> findAll();
}
