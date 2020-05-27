package ru.mpt.convertor.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
@Table(name = "followed_item")
public class FollowedItem {

    @EmbeddedId
    private FollowedItemId id  =  new FollowedItemId();

    @MapsId("userId")
    @JoinColumn(name = "user_id", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    public void setUser(User user) {
        this.user = user;
        getId().setUserId(user.getId());
    }

    public void setItem(Item item) {
        this.item = item;
        getId().setItemId(item.getId());
    }

    @MapsId("itemId")
    @JoinColumn(name = "item_id", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    private Item item;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "follow", nullable = false)
    private boolean following;

    public FollowedItem(User user, Item item) {
        setUser(user);
        setItem(item);
        this.date = LocalDate.now();
        this.following = true;
    }
}
