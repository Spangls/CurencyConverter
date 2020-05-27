package ru.mpt.convertor.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mpt.convertor.model.FollowedItem;
import ru.mpt.convertor.model.FollowedItemId;
import ru.mpt.convertor.model.Item;
import ru.mpt.convertor.model.User;
import ru.mpt.convertor.repos.FollowedItemRepo;
import ru.mpt.convertor.service.FollowedItemService;
import ru.mpt.convertor.service.ItemService;
import ru.mpt.convertor.service.MailSender;
import ru.mpt.convertor.service.UserService;

import java.util.Optional;
import java.util.Set;

@Service
public class FollowedItemServiceImpl implements FollowedItemService {

    private final ItemService itemService;
    private final UserService userService;
    private final FollowedItemRepo followedItemRepo;
    private final MailSender mailSender;

    public FollowedItemServiceImpl(ItemService itemService, UserService userService, FollowedItemRepo followedItemRepo, MailSender mailSender) {
        this.itemService = itemService;
        this.userService = userService;
        this.followedItemRepo  = followedItemRepo;
        this.mailSender = mailSender;
    }

    @Override
    @Transactional
    public FollowedItem getFollowedItemOrCreate(String userEmail, int itemId) {
        User user = userService.findByEmail(userEmail);
        Item item = itemService.getItem(itemId);
        if (user != null && item != null){
            Optional<FollowedItem> followedItem = followedItemRepo.findById(new FollowedItemId(user.getId(), item.getId()));
            return followedItem.orElseGet(()  -> createFollowedItem(user, item));
        }
        return null;
    }

    private FollowedItem createFollowedItem(User user, Item item){
        return followedItemRepo.save(new FollowedItem(user, item));
    }

    @Override
    @Transactional
    public FollowedItem startItemFollow(String userEmail, int itemId) {
        FollowedItem  followedItem =  getFollowedItemOrCreate(userEmail, itemId);
        followedItem.setFollowing(true);
        return followedItem;
    }

    @Override
    @Transactional
    public FollowedItem finishItemFollow(String userEmail, int itemId) {
        FollowedItem followedItem = getFollowedItemOrCreate(userEmail, itemId);
        if (followedItem != null){
            followedItem.setFollowing(false);
            return followedItemRepo.save(followedItem);
        }
        return null;
    }

    @Override
    @Transactional
    public void sendSupplyMessage(int itemId) {
        Item item =  itemService.getItem(itemId);
        if (item != null) {
            Set <FollowedItem> followedItems = followedItemRepo.findAllByItemAndFollowing(item, true);
            followedItems.forEach(followedItem -> {
                mailSender.send(followedItem.getUser().getEmail(), "Поступление отслеживаемого товара", getSupplyMessage(followedItem.getItem(), followedItem.getUser()));
                followedItem.setFollowing(false);
                followedItemRepo.save(followedItem);
            });
        }
    }

    private String getSupplyMessage(Item item, User user){
        return String.format("Доброго времени суток, %s!\n" +
                "Спешим оповестить вас, что отслеживаемый вами товар %s %s поступил на наш склад.\n" +
                "Успейте заказать интересующие вас товары, пока их не раскупили!", user.getFirstName(), item.getManufacturer(), item.getName());
    }
}
