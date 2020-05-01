package ru.mpt.convertor.repos;

import org.springframework.data.repository.CrudRepository;
import ru.mpt.convertor.model.Item;
import ru.mpt.convertor.model.ItemType;

import java.util.Set;

public interface ItemRepo extends CrudRepository<Item, Integer> {
    Set<Item> findAll();

    Set<Item> findAllByType(ItemType type);

    Item findFirstById(Integer id);
}
