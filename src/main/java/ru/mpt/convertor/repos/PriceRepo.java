package ru.mpt.convertor.repos;

import org.springframework.data.repository.CrudRepository;
import ru.mpt.convertor.model.Price;

import java.util.Set;

public interface PriceRepo extends CrudRepository<Price, Integer> {
    Set<Price> findAll();

    Price findFirstByItemIdOrderByDateDesc(Integer itemId);
}
