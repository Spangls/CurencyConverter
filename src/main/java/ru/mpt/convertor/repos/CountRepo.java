package ru.mpt.convertor.repos;

import org.springframework.data.repository.CrudRepository;
import ru.mpt.convertor.model.Count;
import ru.mpt.convertor.model.Item;

import java.util.Set;

public interface CountRepo extends CrudRepository<Count, Integer> {
    Set<Count> findAll();

    Set<Count> findAllByItem(Item item);
}
