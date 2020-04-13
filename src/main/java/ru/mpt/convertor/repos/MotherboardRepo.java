package ru.mpt.convertor.repos;

import org.springframework.data.repository.CrudRepository;
import ru.mpt.convertor.model.*;

import java.util.List;

public interface MotherboardRepo extends CrudRepository<Motherboard, Integer> {
    List<Motherboard> findAllByRamTech(RamTech ramtech);
    List<Motherboard> findAllBySocket(Socket socket);
    List<Motherboard> findAllByFormFactor(MotherboardFormFactor mbFormFactor);
    List<Motherboard> findAllByChipset(Chipset chipset);
}
