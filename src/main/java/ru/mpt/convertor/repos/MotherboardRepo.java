package ru.mpt.convertor.repos;

import org.springframework.data.repository.CrudRepository;
import ru.mpt.convertor.model.*;

import java.util.Set;

public interface MotherboardRepo extends CrudRepository<Motherboard, Integer> {
    Set<Motherboard> findAllByRamTech(RamTech ramtech);

    Set<Motherboard> findAllBySocket(Socket socket);

    Set<Motherboard> findAllByFormFactor(MotherboardFormFactor mbFormFactor);

    Set<Motherboard> findAllByChipset(Chipset chipset);

    Set<Motherboard> findAll();

    Motherboard findFirstById(Integer id);

    Set<Motherboard> findAllByRamTechAndSocket(RamTech ramTech, Socket socket);

    Set<Motherboard> findAllByRamTechAndFormFactor(RamTech ramTech, MotherboardFormFactor mbFormFactor);

    Set<Motherboard> findAllBySocketAndFormFactor(Socket socket, MotherboardFormFactor mbFormFactor);

    Set<Motherboard> findAllByRamTechAndSocketAndFormFactor(RamTech ramTech, Socket socket, MotherboardFormFactor mbFormFactor);
}
