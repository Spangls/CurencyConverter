package ru.mpt.convertor.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mpt.convertor.model.FollowedItem;
import ru.mpt.convertor.model.Item;
import ru.mpt.convertor.model.User;
import ru.mpt.convertor.repos.ItemRepo;
import ru.mpt.convertor.service.CountService;
import ru.mpt.convertor.service.ItemService;
import ru.mpt.convertor.service.UserService;

import java.util.List;
import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepo itemRepo;
    private final CountService countService;
    private final UserService userService;

    public ItemServiceImpl(ItemRepo itemRepo, CountService countService, UserService userService) {
        this.itemRepo = itemRepo;
        this.countService = countService;
        this.userService = userService;
    }

    @Override
    public List<Item> findAll() {
        List<Item> items = itemRepo.findAll();
        items.forEach(this::calculateCount);
        return items;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Item> findAll(String userEmail) {
        List<Item> items = itemRepo.findAll();
        items.forEach(item -> {
            calculateCount(item);
            checkFollow(item, userEmail);
        });
        return items;
    }

    @Override
    public void calculateCount(Item item) {
        item.setCount(countService.findCountByItem(item));
    }

    @Override
    public void checkFollow(Item item, String userEmail) {
        User user = userService.findByEmail(userEmail);
        if (user != null) {
            FollowedItem followedItem = item.findFollowByUser(user);
            if (followedItem != null && followedItem.isFollowing()) {
                item.setFollowed(true);
            }
        }
    }

    @Override
    public Item findFirstById(Integer id) {
        return itemRepo.findFirstById(id);
    }

    @Override
    public Optional<Item> findById(int id) {
        return itemRepo.findById(id);
    }

    @Override
    @Transactional
    public Item save(Item item) {
        return itemRepo.save(item);
    }

    @Override
    public Item getItem(Integer id) {
        return itemRepo.findById(id).orElse(null);
    }
}
