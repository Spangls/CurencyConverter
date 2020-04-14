package ru.mpt.convertor.repos;

import org.springframework.data.repository.CrudRepository;
import ru.mpt.convertor.model.Cpu;
import ru.mpt.convertor.model.Socket;

import java.util.Set;

public interface CpuRepo extends CrudRepository<Cpu, Integer> {
    Set<Cpu> findAll();

    Set<Cpu> findAllBySocket(Socket socket);

    Cpu findFirstById(Integer id);
}
