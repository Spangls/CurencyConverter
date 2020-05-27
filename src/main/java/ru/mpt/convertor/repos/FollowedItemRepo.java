package ru.mpt.convertor.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import ru.mpt.convertor.model.FollowedItem;
import ru.mpt.convertor.model.FollowedItemId;
import ru.mpt.convertor.model.Item;

import java.util.Set;

public interface FollowedItemRepo extends CrudRepository<FollowedItem, FollowedItemId>, JpaRepository<FollowedItem, FollowedItemId> {
    Set<FollowedItem> findAllByItemAndFollowing(Item item, Boolean following);
}
