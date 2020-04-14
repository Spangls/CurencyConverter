package ru.mpt.convertor.repos;

import org.springframework.data.repository.CrudRepository;
import ru.mpt.convertor.model.Item;

import java.util.List;

public interface ItemRepo extends CrudRepository<Item, Integer> {
    List<Item> findAll();

    Item findFirstById(Integer id);
}
