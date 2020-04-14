package ru.mpt.convertor.repos;

import org.springframework.data.repository.CrudRepository;
import ru.mpt.convertor.model.Gpu;

import java.util.Set;

public interface GpuRepo extends CrudRepository<Gpu, Integer> {
    Set<Gpu> findAll();

    Gpu findFirstById(Integer id);
}
