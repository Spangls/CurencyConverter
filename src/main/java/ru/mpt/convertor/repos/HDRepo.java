package ru.mpt.convertor.repos;

import org.springframework.data.repository.CrudRepository;
import ru.mpt.convertor.model.HardDrive;

import java.util.List;

public interface HDRepo extends CrudRepository<HardDrive, Integer> {
    List<HardDrive> findAll();

    HardDrive findFirstById(Integer id);
}
