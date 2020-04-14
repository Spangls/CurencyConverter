package ru.mpt.convertor.repos;

import org.springframework.data.repository.CrudRepository;
import ru.mpt.convertor.model.HardDrive;

import java.util.Set;

public interface HDRepo extends CrudRepository<HardDrive, Integer> {
    Set<HardDrive> findAll();

    HardDrive findFirstById(Integer id);
}
