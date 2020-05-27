package ru.mpt.convertor.service;

import ru.mpt.convertor.model.Item;

import java.util.List;
import java.util.Optional;

public interface ItemService {

    List<Item> findAll();
    List<Item> findAll(String userEmail);

    void calculateCount(Item item);
    void checkFollow(Item item, String userEmail);

    Item findFirstById(Integer id);

    Optional<Item> findById(int id);

    Item save(Item item);

    Item getItem(Integer id);


}
