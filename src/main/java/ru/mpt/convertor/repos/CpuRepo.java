package ru.mpt.convertor.repos;

import org.springframework.data.repository.CrudRepository;
import ru.mpt.convertor.model.Cpu;
import ru.mpt.convertor.model.Socket;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public interface CpuRepo extends CrudRepository<Cpu, Integer> {
    List<Cpu> findAll();

    Cpu findFirstById(Integer id);
}
