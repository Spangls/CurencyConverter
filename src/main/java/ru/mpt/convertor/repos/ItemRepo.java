package ru.mpt.convertor.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import ru.mpt.convertor.model.Item;
import ru.mpt.convertor.model.ItemType;

import java.util.Optional;
import java.util.Set;

public interface ItemRepo extends CrudRepository<Item, Integer>, JpaRepository<Item, Integer>{
    Set<Item> findAllByType(ItemType type);
    Item findFirstById(Integer id);
}
