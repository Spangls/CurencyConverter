package ru.mpt.convertor.repos;

import org.springframework.data.repository.CrudRepository;
import ru.mpt.convertor.model.Case;
import ru.mpt.convertor.model.MotherboardFormFactor;

import java.util.Set;

public interface CaseRepo extends CrudRepository<Case, Integer> {
    Set<Case> findAll();

    Set<Case> findAllByMotherboardFormFactor(MotherboardFormFactor mbFormFactor);

    Case findFirstById(Integer id);
}
