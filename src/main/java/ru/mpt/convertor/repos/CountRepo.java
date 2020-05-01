package ru.mpt.convertor.repos;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.mpt.convertor.model.Count;

import java.util.Set;

public interface CountRepo extends CrudRepository<Count, Integer> {
    Set<Count> findAll();
    @Query(value = "SELECT sum(change) FROM Count WHERE id=?1")
    Set<Count> sumCount(Integer itemId);
}
