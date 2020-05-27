package ru.mpt.convertor.service;

import ru.mpt.convertor.model.FollowedItem;

public interface FollowedItemService {
    FollowedItem getFollowedItemOrCreate(String userEmail, int itemId);

    FollowedItem startItemFollow(String userEmail, int itemId);

    FollowedItem finishItemFollow(String userEmail, int itemId);

    void sendSupplyMessage(int itemId);
}
